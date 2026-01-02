package com.vidura.lmsbackend.filters; // or your package name

import com.vidura.lmsbackend.dto.ServerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServerResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // 1. Get the list of all errors
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        // 2. Set the "message" to the FIRST error found (if any exist)
        String displayMessage = "Validation Failed";
        if (!allErrors.isEmpty()) {
            displayMessage =((FieldError) allErrors.get(0)).getField()  + " " +allErrors.get(0).getDefaultMessage();
        }

        // 3. Populate the map with all errors (for the 'data' field)
        allErrors.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // 4. Build the response
        ServerResponse<Map<String, String>> response = ServerResponse.<Map<String, String>>builder()
                .status(0)
                .message(displayMessage) // <--- Now contains the single error message
                .data(errors)
                .build();

        return ResponseEntity.ok().body(response);
    }

    // Handle generic exceptions (Fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServerResponse<String>> handleGeneralException(Exception ex) {
        ServerResponse<String> obj = new ServerResponse<>();
        return ResponseEntity.ok(obj.fromException(ex));
    }
}