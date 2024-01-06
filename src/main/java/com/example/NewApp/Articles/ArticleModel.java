package com.example.NewApp.Articles;

import jakarta.persistence.Lob;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleModel {

    private Long id;
    private String title;
    private String content;
    private String date;
    private String time;
    private String author;
    private String images;

}
