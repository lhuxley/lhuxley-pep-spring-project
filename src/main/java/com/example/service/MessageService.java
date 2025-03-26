package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import com.example.entity.Message;
import java.util.Optional;
import com.example.repository.MessageRepository;
import java.util.List;


@Service
public class MessageService {
    MessageRepository messageRepository;


    @Autowired
    public MessageService(MessageRepository messageRepository) {

        
        this.messageRepository = messageRepository;
        
    }

    @Transactional
    public ResponseEntity<Message> createMessage(Message message){

        if(message.getMessageText().length() == 0 || message.getMessageText().length() > 255 || !messageRepository.existsByPostedBy(message.getPostedBy()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        
        return ResponseEntity.status(HttpStatus.OK).body(messageRepository.save(message));
        
    }

    @Autowired
    public ResponseEntity<List<Message>> getAllMessages(){

        return ResponseEntity.status(HttpStatus.OK).body(messageRepository.findAll());
        
    }

    
    public ResponseEntity<Message> getMessageByMessageId(Integer messageId){
        Optional<Message> message = messageRepository.findByMessageId(messageId);

        if(message.isEmpty())
            return ResponseEntity.status(200).build();

        return ResponseEntity.status(HttpStatus.OK).body(message.get());
        
    }

    @Transactional
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer messageId){
        Optional<Message> message = messageRepository.findByMessageId(messageId);
        

        if(message.isEmpty())
            return ResponseEntity.status(200).build();


        messageRepository.deleteByMessageId(messageId);   
        return ResponseEntity.status(200).body(1);
        
    }

    @Modifying
    public ResponseEntity<Integer> patchMessage(Message message, @PathVariable Integer messageId){
    
        if(messageRepository.findByMessageId(messageId).isEmpty() || message.getMessageText().length() == 0 || message.getMessageText().length() > 255)
            return ResponseEntity.status(400).build();

       
        messageRepository.updateMessageContentById(messageId, message.getMessageText());
 
        return ResponseEntity.status(200).body(1);
        
    }

}
