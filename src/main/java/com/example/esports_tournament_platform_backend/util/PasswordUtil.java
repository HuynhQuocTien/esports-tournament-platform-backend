package com.example.esports_tournament_platform_backend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Hash password với BCrypt
     * @param rawPassword mật khẩu gốc
     * @return mật khẩu đã hash
     */
    public static String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * So sánh mật khẩu gốc với mật khẩu đã hash
     * @param rawPassword mật khẩu người dùng nhập
     * @param hashedPassword mật khẩu hash lưu trong DB
     * @return true nếu khớp, false nếu sai
     */
    public static boolean matches(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
