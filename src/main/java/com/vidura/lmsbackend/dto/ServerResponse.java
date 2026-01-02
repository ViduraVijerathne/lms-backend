package com.vidura.lmsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServerResponse<T> {
    private  int status;
    private String message;
    private T data;

    public void setData(T data) {
        fromSuccess(data);
    }

    public  ServerResponse<T> fromException(Exception e){
        this.status = 0;
        this.message = e.getMessage();
        this.data = null;
        return  this;
    }

    public  ServerResponse<T> fromSuccess(T data){
        this.status = 1;
        this.data = data;
        this.message = "success";
        return  this;
    }
}
