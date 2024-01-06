package com.example.NewApp.Journalist;

import com.example.NewApp.Security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JournalistDAOTest {

    @Autowired
    JournalistDAO journalistDAO;

    @Test
    public void saveJournalist(){
        Journalist journalist = Journalist.builder()
                .firstName("M")
                .middleName("A")
                .lastName("A")
                .phoneNumber("08147")
                .country("Nigeria")
                .state("Niger")
                .username("Musantor")
                .password("1234")
                .role(UserRole.JOURNALIST)
                .build();

        journalistDAO.save(journalist);
    }


}