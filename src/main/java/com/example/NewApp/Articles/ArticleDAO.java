package com.example.NewApp.Articles;

import org.apache.catalina.LifecycleState;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDAO extends JpaRepository<Article, Long> {

//    List<Article> fetchSomePages(Pageable pageable);
}
