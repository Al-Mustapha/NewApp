package com.example.NewApp.Message;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/message/")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody Message message){
        messageService.sendMessage(message);
        return ResponseEntity.ok("Your message has been delivered!");
    }

    @GetMapping("getAllMessages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping("getMessageById/{id}")
    public Message getMessageById(@PathVariable("id") Long id){
        return messageService.getMessageById(id);
    }

    @GetMapping("getCount")
    public int countLatestMessages(){
        return messageService.countLatestMessages();
    }



}
