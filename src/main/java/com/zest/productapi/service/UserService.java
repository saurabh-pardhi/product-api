package com.zest.productapi.service;

import com.zest.productapi.dto.UserRegisterRequest;
import com.zest.productapi.dto.UserRegisterResponse;

public interface UserService {
    UserRegisterResponse registerUser(UserRegisterRequest request);
}