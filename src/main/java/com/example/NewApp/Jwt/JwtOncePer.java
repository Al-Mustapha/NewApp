package com.example.NewApp.Jwt;

import com.example.NewApp.Reader.ReaderService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
@ComponentScan(basePackages = {"com.example.NewApp.Jwt"})
public class JwtOncePer extends OncePerRequestFilter {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token;
        String user;


        try {
            String header = request.getHeader("authorization");
//            System.out.println(header);
            if (header == null || !header.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }
            token = header.substring(7);
            user = jwtUtils.extractUsername(token);

            if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails applicationUser = readerService.loadUserByUsername(user);

                if(jwtUtils.isTokenValid(token, applicationUser)) {
                    UsernamePasswordAuthenticationToken us =
                            new UsernamePasswordAuthenticationToken(applicationUser, null,
                                    applicationUser.getAuthorities());
                    us.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(us);
                }
            }

        } catch (UsernameNotFoundException e) {
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);
//        System.out.println(user);
    }
    }
