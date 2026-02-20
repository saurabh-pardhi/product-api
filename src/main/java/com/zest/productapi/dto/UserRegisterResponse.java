package com.zest.productapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class UserRegisterResponse {
    private String username;
    private String message;

    public UserRegisterResponse(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}