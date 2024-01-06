package com.example.NewApp.Jwt;

import com.example.NewApp.Reader.MapReaderToSecurity;
import com.example.NewApp.Reader.Reader;
import com.example.NewApp.Reader.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticateUser {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ReaderService readerService;

        @Autowired
        private JwtUtils jwtUtils;
        String s = null;



    @PostMapping("/authenticate")
    public String authenticate(@RequestBody UsRequest request){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Reader users = (Reader) readerService.loadUserByUsername(request.getUsername());

        if(users != null){
            return jwtUtils.generateToken(users.getUsername());
        }
        else {
            return "No User with such details exist";
        }

    }

}
