package com.example.NewApp.Articles;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDAO articleDAO;
    public List<Article> getAllArticles() {
        return articleDAO.findAll();
    }

    public void createArticle(String title, String content, String file) throws IOException {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setFile(file);
        article.setDate(String.valueOf(LocalDate.now()));
        article.setTime(String.valueOf(LocalTime.now()));
//        article.setAuthor(model.getAuthor());
        articleDAO.save(article);
    }

    public Article getArticleById(Long id) {
        return articleDAO.findById(id).get();
    }

    public void deleteAllArticles() {
        articleDAO.deleteAll();
    }

    public void deleteArticleById(Long id) {
        articleDAO.deleteById(id);
    }

    public void updateArticle(Long id, String title, String content, String file) {
        Article articleFetched = articleDAO.findById(id).get();
        articleFetched.setTitle(title);
        articleFetched.setFile(file);
        articleFetched.setContent(content);
        articleFetched.setDate(String.valueOf(LocalDate.now()));
        articleFetched.setTime(String.valueOf(LocalTime.now()));
        articleFetched.setAuthor("null");
        articleDAO.save(articleFetched);
    }

    public List<Article> getSixArticles() {
        Pageable pageable = PageRequest.of(0,9);
        List<Article> allArticles = articleDAO.findAll(pageable).getContent();
        return allArticles;
    }
}
