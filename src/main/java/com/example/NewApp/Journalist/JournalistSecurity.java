package com.example.NewApp.Journalist;

import com.example.NewApp.Jwt.JwtOncePer;
import com.example.NewApp.Reader.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor
public class JournalistSecurity {
    @Autowired
    private JwtOncePer jwtOncePer;
    @Autowired
    private final JournalistService journalistService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtOncePer, UsernamePasswordAuthenticationFilter.class);
                return httpSecurity.build();
    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider2(){
//        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//        dao.setUserDetailsService(journalistService);
//        dao.setPasswordEncoder(passwordEncoder);
//        return dao;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager2(JournalistService journalistService){
//        var authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(journalistService);
//        return new ProviderManager(authProvider);
//    }

}
