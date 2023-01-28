package com.front.end.pk.encrypt.demo.fepke_api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
    @Bean
    BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
