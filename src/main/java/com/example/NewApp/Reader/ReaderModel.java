package com.example.NewApp.Reader;

import com.example.NewApp.Security.UserRole;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReaderModel {
    private Long id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String dob;
    private String country;
    private String state;
    private String phonenumber;
    private String username;
    private String password;
    private UserRole role;
    private boolean enabled = false;
}

