package com.example.NewApp.Articles;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import static java.nio.file.Paths.get;

@RestController
@RequestMapping("/article/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ArticleController {

    private final String DIRECTORY = System.getProperty("user.home") + "/desktop/uploads/";

    MultipartResolver multipartResolver;
    private final ArticleService articleService;

    @GetMapping("getAllArticles")
    @PreAuthorize("hasRole('JOURNALIST')")
    public List<Article> getAllArticles() throws IOException {
        return articleService.getAllArticles();
    }

    @GetMapping("getOneArticle/{id}")
    @PreAuthorize("hasAnyRole('READER','JOURNALIST')")
    public Article getArticleById(@PathVariable("id") Long id){
        return articleService.getArticleById(id);
    }

    @PutMapping("editArticle/{id}")
    @PreAuthorize("hasRole('JOURNALIST')")
    public String editArticle(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("file") MultipartFile file,
                              @RequestParam("content") String content
                              ) throws IOException {
        Path pathForEdit = get("C:\\Users\\shopinverse.com\\Desktop\\NewApp\\frontend\\public\\images\\", file.getOriginalFilename()).toAbsolutePath();
        file.transferTo(pathForEdit);
        articleService.updateArticle(id, title, content, file.getOriginalFilename());
        return "Article has been updated successfully";
    }

    @DeleteMapping("deleteAllArticles")
    @PreAuthorize("hasRole('JOURNALIST')")
    public String deleteAllArticle(){
        articleService.deleteAllArticles();
        return "All articles have been deleted";
    }

    @DeleteMapping("deleteAnArticle/{id}")
    @PreAuthorize("hasRole('JOURNALIST')")
    public String deleteArticleById(@PathVariable("id") Long id){
        articleService.deleteArticleById(id);
        return "The Article has been deleted successfully";
    }

    @PostMapping(value = "createArticle", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('JOURNALIST')")
    public String createArticle(@RequestParam("title") String title, @RequestParam("file") MultipartFile file,
                                @RequestParam("content") String content
                                ) throws IOException {

        String filename = file.getOriginalFilename();
        Path path2 = get("C:\\Users\\shopinverse.com\\Desktop\\NewApp\\frontend\\public\\images\\", filename).toAbsolutePath();
        file.transferTo(path2);
        String content1 = content.toLowerCase();
        content1.substring(0,1).toUpperCase();
        articleService.createArticle(title, content1, filename);
        return "Article has successfully been created";
    }


    @GetMapping("getSixArticles")
    public List<Article> pageWithSixArticles (){
        return articleService.getSixArticles();
    }
}





