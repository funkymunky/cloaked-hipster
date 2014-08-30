package net.helloworld.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashCodeService {

    public static String getHashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        return hashedPassword;
    }

}