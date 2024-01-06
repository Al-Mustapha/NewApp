package com.example.NewApp.Event;

import com.example.NewApp.Reader.Reader;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationEvent extends ApplicationEvent {

    private Reader reader;
    private String applicationUrl;

    public RegistrationEvent(Reader reader, String applicationUrl) {
        super(reader);
        this.reader = reader;
        this.applicationUrl = applicationUrl;
    }
}
