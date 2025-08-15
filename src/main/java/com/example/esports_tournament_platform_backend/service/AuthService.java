package com.example.esports_tournament_platform_backend.service;

import com.example.esports_tournament_platform_backend.entity.User;
import com.example.esports_tournament_platform_backend.repository.UserRepository;
import com.example.esports_tournament_platform_backend.util.EmailUtil;
import com.example.esports_tournament_platform_backend.util.JwtUtil;
import com.example.esports_tournament_platform_backend.util.PasswordUtil;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final EmailUtil emailUtil;


    public void register(String username, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email đã tồn tại");
        }
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username đã tồn tại");
        }

        User user = User.builder()
                .username(username)
                .email(email)
                .password(PasswordUtil.hashPassword(password))
                .build();
        userRepository.save(user);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));

        if (!PasswordUtil.matches(password, user.getPassword())) { // ✅ So sánh hash
            throw new RuntimeException("Sai mật khẩu");
        }

        return jwtUtil.generateToken(username);
    }

    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy email"));

        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        userRepository.save(user);

        String resetLink = "http://localhost:8080/api/auth/reset-password?token=" + token;
        emailUtil.sendEmail(
                email,
                "Đặt lại mật khẩu",
                "Click vào link để đặt lại mật khẩu: " + resetLink
        );
    }

    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new RuntimeException("Token không hợp lệ"));

        user.setPassword(PasswordUtil.hashPassword(newPassword));
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
}

