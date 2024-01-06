package com.example.NewApp.Verification;

import com.example.NewApp.Reader.Reader;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    private LocalDateTime expirationTime;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false)
    private Reader reader;

    public VerificationToken(Reader reader, String token){
        this.reader = reader;
        this.token = token;
        this.expirationTime = LocalDateTime.now().plusMinutes(10);
    }

    public VerificationToken(String token){
        this.token = token;
        this.expirationTime = LocalDateTime.now().plusMinutes(10);
    }

}
