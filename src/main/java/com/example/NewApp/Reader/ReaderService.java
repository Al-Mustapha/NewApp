package com.example.NewApp.Reader;


import com.example.NewApp.PasswordReset.PasswordResetToken;
import com.example.NewApp.PasswordReset.PasswordResetTokenRepository;
import com.example.NewApp.Security.UserRole;
import com.example.NewApp.Verification.VerificationToken;
import com.example.NewApp.Verification.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class ReaderService implements UserDetailsService{

    private final VerificationTokenRepository verificationTokenRepository;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    ReaderDAO readerDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Reader> getAllReaders() {
        return readerDAO.findAll();
    }
    public void createReader(ReaderModel model) {
        Reader reader = new Reader();
        reader.setFirstName(model.getFirstname());
        reader.setMiddleName(model.getMiddlename());
        reader.setLastName(model.getLastname());
        reader.setPhoneNumber(model.getPhonenumber());
        reader.setDob(model.getDob());
        reader.setState(model.getState());
        reader.setCountry(model.getCountry());
        reader.setUsername(model.getUsername());
        reader.setPassword(passwordEncoder.encode(model.getPassword()));
        reader.setRole(UserRole.READER);
//        BeanUtils.copyProperties(model, reader);
        readerDAO.save(reader);
    }
    public Reader getReaderById(Long id) {
        return readerDAO.findById(id).get();
    }
    public void updateReader(Reader reader1) {
        readerDAO.save(reader1);
    }
    public void deleteAllReaders() {
        readerDAO.deleteAll();
    }
    public void deleteReaderById(Long id) {
        readerDAO.deleteById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader reader = readerDAO.getUserByUsername(username);
        if (reader.isEnabled())
            return reader;
        else
            return null;
    }

    public void saveReaderWithToken(Reader reader, String token) {
        reader.setRole(UserRole.READER);
        reader.setPassword(passwordEncoder.encode(reader.getPassword()));
        VerificationToken verificationToken =
                new VerificationToken(reader, token);
        verificationTokenRepository.save(verificationToken);
    }
    public String validateToken(String token) {
        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(token);
        Reader reader = verificationToken.getReader();
        if (verificationToken == null)
            return "NO VERIFICATION TOKEN FOUND";
        else if (verificationToken.getExpirationTime().getMinute() - LocalDateTime.now().getMinute() <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return "Token expired";
        }
        else {
            reader.setEnabled(true);
            readerDAO.save(reader);
            return "VALID";
        }

    }

    public Reader findReaderByEmail(String email) {
        Reader reader = readerDAO.findReaderByEmail(email);
        return reader;
    }

    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken =
                passwordResetTokenRepository.findByToken(token);
        Reader reader = passwordResetToken.getReader();
        if (passwordResetToken == null){
            return "NO TOKEN FOUND";
        }
        else if (passwordResetToken.getExpirationTime().getMinute() - LocalDateTime.now().getMinute() <=0) {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "TOKEN HAS EXPIRED";
        }
        return "VALID";
    }

    public void saveReaderWithPasswordResetToken(Reader reader, String token) {
        PasswordResetToken passwordResetToken
                = new PasswordResetToken(reader, token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    public Reader findReaderByToken(String token) {
        PasswordResetToken passwordResetToken
                = passwordResetTokenRepository.findByToken(token);
        return passwordResetToken.getReader();
    }
    public void changePassword(Reader reader, String newPassword) {
        reader.setPassword(passwordEncoder.encode(newPassword));
    }
}
