package com.example.NewApp.Reader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderClass {

    @Bean
    public PasswordEncoder passwordEncoder(){
//        DelegatingPasswordEncoder encoder =
//                (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        BCryptPasswordEncoder bCryptPasswordEncoder =
//                new BCryptPasswordEncoder(20);
//        encoder.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder);
//        return encoder;
        return new BCryptPasswordEncoder(20);
    }
}
