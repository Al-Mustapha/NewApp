package com.example.NewApp.PasswordReset;

import com.example.NewApp.Reader.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
