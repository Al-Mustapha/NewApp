package com.example.NewApp.Jwt;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsRequest {
    private String username;
    private String password;
}

