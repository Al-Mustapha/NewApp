package com.example.NewApp.Event;

import com.example.NewApp.Reader.Reader;
import com.example.NewApp.Reader.ReaderService;
import com.example.NewApp.Verification.VerificationToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Component
public class RegistrationEventListener implements ApplicationListener<RegistrationEvent> {

    @Autowired
    private final ReaderService readerService;


    @Override
    public void onApplicationEvent(RegistrationEvent event) {

        Reader reader = event.getReader();
        String token = UUID.randomUUID().toString();
        readerService.saveReaderWithToken(reader, token);

        //Send Link
        String url = event.getApplicationUrl()
                + "verifyRegistration?token="
                + token;

      log.info("Click the link to verify: {}", url);

    }
}
