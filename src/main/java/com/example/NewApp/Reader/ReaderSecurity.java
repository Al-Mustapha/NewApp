package com.example.NewApp.Reader;

import com.example.NewApp.Journalist.JournalistService;
import com.example.NewApp.Jwt.JwtOncePer;
import com.example.NewApp.Security.UserRole;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.log.UserDataHelper;
import org.hibernate.metamodel.model.domain.ManagedDomainType;
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
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static com.example.NewApp.Security.UserRole.*;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@AllArgsConstructor
public class ReaderSecurity {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private final ReaderService readerService;
    @Autowired
    private final JournalistService journalistService;

    @Autowired
    private JwtOncePer jwtOncePer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity.csrf().disable()

                 .authenticationProvider(daoAuthenticationProvider())
                 .authorizeHttpRequests()
//                 .requestMatchers("/article/**").hasRole(JOURNALIST.name())
//                 .requestMatchers("/article/getSixArticles").hasRole(READER.name())
                 .anyRequest().authenticated()
                 .and()
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtOncePer, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user = User.builder()
//                .username("Aliyu")
//                .password(passwordEncoder.encode("password"))
//                .roles(READER.name())
////                .authorities(READER.getAuthorities())
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("Musa")
//                .password(passwordEncoder.encode("password"))
//                .roles(JOURNALIST.name())
////                .authorities(JOURNALIST.getAuthorities())
//                .build();
//        return new InMemoryUserDetailsManager(user, user2);
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(readerService);
//        dao.setUserDetailsService(journalistService);
        dao.setPasswordEncoder(passwordEncoder);
        return dao;
    }

    @Bean
    public AuthenticationManager authenticationManager(ReaderService readerService){
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(readerService);
        return new ProviderManager(authProvider);
    }

}
