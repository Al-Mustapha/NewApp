package com.example.NewApp.PasswordReset;

import com.example.NewApp.Reader.Reader;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    private LocalDateTime expirationTime;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private Reader reader;

    public PasswordResetToken(Reader reader, String token){
        this.reader = reader;
        this.token = token;
        this.expirationTime = LocalDateTime.now().plusMinutes(10);
    }

    public PasswordResetToken(String token){
        this.token = token;
        this.expirationTime = LocalDateTime.now().plusMinutes(10);
    }


}
