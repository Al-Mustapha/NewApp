package com.example.NewApp.Reader;

import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderDAO extends JpaRepository<Reader, Long> {

    @Query(
            name = "my_query",
            nativeQuery = true,
            value = "Select * from reader where username=:username and password=:password"
    )
    Reader getReaderByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(
            name = "my_query",
            nativeQuery = true,
            value = "Select * from reader r where r.username=:username"
    )
    Reader getUserByUsername(@Param("username") String username);


    Reader findReaderByEmail(String email);
}
