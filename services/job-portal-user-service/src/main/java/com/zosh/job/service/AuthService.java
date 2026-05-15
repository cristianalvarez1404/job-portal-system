package com.zosh.job.service;

public interface AuthService {
    AuthResponse signup(SignupRequest req);
    AuthResponse login(LoginRequest req);
}
