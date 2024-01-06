package com.example.NewApp.Reader;

import com.example.NewApp.Security.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReaderDAOTest {

    @Autowired
    private ReaderDAO readerDAO;

    @Test
    public void addReader(){
        Reader reader = Reader.builder()
                .firstName("your")
                .role(UserRole.JOURNALIST)
                .username("mine")
                .password("123456")
                .build();
        readerDAO.save(reader);
    }
}