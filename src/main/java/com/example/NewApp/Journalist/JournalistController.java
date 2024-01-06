package com.example.NewApp.Journalist;

import com.example.NewApp.Articles.Article;
import com.example.NewApp.Articles.ArticleDAO;
import com.example.NewApp.Articles.ArticleService;
import com.example.NewApp.Reader.Reader;
import com.example.NewApp.Reader.ReaderModel;
import com.example.NewApp.Reader.ReaderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journalist/")
public class JournalistController {

    @Autowired
    JournalistService journalistService;

    @PutMapping("editProfile/{id}")
    public String editProfile(@PathVariable("id") Long id, @RequestBody Journalist journalist){
        Journalist journo = journalistService.getJournalistById(id);
        journo.setFirstName(journalist.getFirstName());
        journo.setMiddleName(journalist.getMiddleName());
        journo.setLastName(journalist.getLastName());
        journo.setCountry(journalist.getCountry());
        journo.setState(journalist.getState());
        journo.setPhoneNumber(journalist.getPhoneNumber());
        journo.setDob(journalist.getDob());
        journalistService.updateJournalist(journo);
        return "Your profile has been updated successfully";
    }


}
