package com.example.NewApp.Journalist;

import com.example.NewApp.Reader.Reader;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JournalistDAO extends JpaRepository<Journalist, Long> {

    @Query(
            name = "my_query",
            nativeQuery = true,
            value = "Select * from journalist s where s.username=:username"
    )
    Reader getUserByUsername(@Param("username") String username);

    @Query("select s from Journalist s where s.username=:username")
    Reader getUser(@Param("username") String username);

}
