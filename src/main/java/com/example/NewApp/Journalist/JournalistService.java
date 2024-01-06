package com.example.NewApp.Journalist;

import com.example.NewApp.Security.UserRole;
import jakarta.persistence.SecondaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalistService implements UserDetailsService {

    @Autowired
    JournalistDAO journalistDAO;
    public Journalist getJournalistById(Long id) {
        return journalistDAO.findById(id).get();
    }

    public void updateJournalist(Journalist journo) {
        journalistDAO.save(journo);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) journalistDAO.getUser(username);
    }
}
