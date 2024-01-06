package com.example.NewApp.Articles;

import com.example.NewApp.Journalist.Journalist;
import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Entity
@Data
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;

    private String date;
    private String time;
    private String author;
//    @Lob
    private String file;

//    @ManyToOne(
//         cascade = CascadeType.ALL,
//         fetch = FetchType.LAZY,
//            optional = false
//    )
//            @JoinColumn(
//                    name = "author_id",
//                    referencedColumnName = "id"
//            )
//    Journalist journalist;

}
