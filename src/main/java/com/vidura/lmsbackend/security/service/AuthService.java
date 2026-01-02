package com.vidura.lmsbackend.security.service;

import com.vidura.lmsbackend.dto.TeacherDTO;
import com.vidura.lmsbackend.dto.register.TeacherRegisterDTO;
import com.vidura.lmsbackend.entity.Subject;
import com.vidura.lmsbackend.entity.Teacher;
import com.vidura.lmsbackend.repository.SubjectRepository;
import com.vidura.lmsbackend.repository.TeacherRepository;
import com.vidura.lmsbackend.security.dto.AuthResponse;
import com.vidura.lmsbackend.security.dto.LoginRequest;
import com.vidura.lmsbackend.security.dto.RegisterRequest;
import com.vidura.lmsbackend.security.entity.Role;
import com.vidura.lmsbackend.security.entity.User;
import com.vidura.lmsbackend.security.jwt.JwtUtil;
import com.vidura.lmsbackend.security.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }

    @Transactional(rollbackFor = Exception.class)
    public TeacherDTO createTeacher(TeacherRegisterDTO dto){
        // 1. Validate Username
        if (userRepository.existsByUsername(dto.getEmail())) {
            throw new RuntimeException("Username already exists");
        }

        // 2. Validate Subject
        Optional<Subject> subject = subjectRepository.findById((long) dto.getSubjectID());
        if (subject.isEmpty()) {
            throw new RuntimeException("Subject not found");
        }

        // 3. Save User (First Transaction Step)
        User user = new User();
        user.setUsername(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.TEACHER);
        User savedUser = userRepository.save(user); // Save returned instance is better practice

        // 4. Save Teacher (Second Transaction Step)
        try {
            Teacher teacher = new Teacher();
            teacher.setUser(savedUser);
            teacher.setEmail(dto.getEmail());
            teacher.setTags(new ArrayList<>());
            teacher.setName(dto.getName());
            teacher.setSubject(subject.get());
            teacher.setProfileURL("https://avatar.iran.liara.run/public/28");
            teacherRepository.save(teacher);

            return teacher.toDTO();

        } catch (Exception e) {
            // If saving teacher fails, the @Transactional annotation
            // will automatically delete the 'savedUser' created in step 3.
            throw e;
        }
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
                "anonymousUser".equals(authentication.getPrincipal())) {
            throw new RuntimeException("No user is currently logged in");
        }

        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found in database"));
    }

    public AuthResponse getMe() {
        User user = getCurrentUser();

        return new AuthResponse(null, user.getUsername(), user.getRole().name());
    }
}
