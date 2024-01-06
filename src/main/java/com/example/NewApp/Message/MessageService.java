package com.example.NewApp.Message;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepo messageRepo;
    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    public Message getMessageById(Long id) {
        return messageRepo.findById(id).get();
    }

    public int countLatestMessages() {
//        Pageable pageable = PageRequest.of(8,2);
        return messageRepo.findAll().size();
    }

    public void sendMessage(Message message) {
        messageRepo.save(message);
    }
}
