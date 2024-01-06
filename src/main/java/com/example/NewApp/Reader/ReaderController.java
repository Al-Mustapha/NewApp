package com.example.NewApp.Reader;

import com.example.NewApp.Articles.Article;
import com.example.NewApp.Articles.ArticleService;
import com.example.NewApp.Event.RegistrationEvent;
import com.example.NewApp.Password.PasswordModel;
import com.example.NewApp.Verification.VerificationToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/reader/")
@CrossOrigin("http://localhost:3000")
public class ReaderController {
    @Autowired
    ReaderService readerService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("createAccount")
    public String createAccount(@RequestBody Reader reader, HttpServletRequest request){
        publisher.publishEvent(new RegistrationEvent(
                reader,
                applicationUrl(request)
        ));
        return "Account Created Successfully!";
    }
    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName()
                + ":" + request.getServerPort()
                + "/" + "reader/";
    }

    @GetMapping("resetPassword/{email}")
    public String resetPassword(@PathVariable("email") String email, HttpServletRequest request){
        Reader reader = readerService.findReaderByEmail(email);
        String url = "";
        if (reader !=null){
            String token = UUID.randomUUID().toString();
            readerService.saveReaderWithPasswordResetToken(reader, token);
            url = "http://"
            + request.getServerName()
            + ":" + request.getServerPort()
            + "/reader/" + "resetPassword?token=" + token;
        }
        log.info("Click this link to reset your password {}", url);
        return url;
    }

    @PostMapping("resetPassword")
    public String changePassword(@RequestParam("token") String token, @RequestBody PasswordModel passwordModel){
        String result = readerService.validatePasswordResetToken(token);
        String forRet = "";
        if (result.equalsIgnoreCase("VALID")){
            Reader reader = readerService.findReaderByToken(token);
            if (reader != null) {
                readerService.changePassword(reader, passwordModel.getNewPassword());
                forRet = "Password Changed Successfully";            }
        }
        return forRet;
    }


    @GetMapping("verifyRegistration")
    public String verifyRegistrationToken(@RequestParam("token") String token){
        String result = readerService.validateToken(token);
        if (result.equalsIgnoreCase("VALID"))
            return "YOUR ACCOUNT HAS BEEN ACTIVATED";
        else
            return "ERROR ACTIVATING ACCOUNT";
    }

    @PutMapping("editProfile/{id}")
    public String updateProfile(@PathVariable("id") Long id, @RequestBody Reader reader){
        Reader reader1 = readerService.getReaderById(id);
        reader1.setFirstName(reader.getFirstName());
        reader1.setMiddleName(reader.getMiddleName());
        reader1.setLastName(reader.getLastName());
        reader1.setDob(reader.getDob());
        reader1.setCountry(reader.getCountry());
        reader1.setState(reader.getState());
        reader1.setPhoneNumber(reader.getPhoneNumber());
        readerService.updateReader(reader1);
        return "Your profile has been updated!";
    }

    @GetMapping("getAllReaders")
    @PreAuthorize("hasRole('JOURNALIST')")
    public List<Reader> getAllReaders(){
        return readerService.getAllReaders();
    }

    @DeleteMapping("deleteAllReaders/{id}")
    @PreAuthorize("hasRole('JOURNALIST')")
    public String deleteAllReaders(@PathVariable("id") Long id){
        readerService.deleteAllReaders();
        return "All Readers have been deleted";
    }

    @DeleteMapping("deleteReaderById/{id}")
    @PreAuthorize("hasRole('JOURNALIST')")
    public String deleteReaderById(@PathVariable("id") Long id){
        readerService.deleteReaderById(id);
        return "The reader has been deleted";
    }
}
