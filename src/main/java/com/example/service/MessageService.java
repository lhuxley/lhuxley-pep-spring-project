package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;
import java.util.Optional;
import com.example.repository.MessageRepository;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public Message createMessage(Message message) {
        if (message.getMessageText().isEmpty() || message.getMessageText().length() > 255
                || !messageRepository.existsByPostedBy(message.getPostedBy())) {
            return null;
        }
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageByMessageId(Integer messageId) {
        return messageRepository.findByMessageId(messageId);
    }

    @Transactional
    public boolean deleteMessage(Integer messageId) {
        Optional<Message> message = messageRepository.findByMessageId(messageId);
        if (message.isPresent()) {
            messageRepository.deleteByMessageId(messageId);
            return true;
        }
        return false;
    }

    @Modifying
    public boolean patchMessage(Message message, Integer messageId) {
        if (messageRepository.findByMessageId(messageId).isEmpty() || message.getMessageText().isEmpty()
                || message.getMessageText().length() > 255) {
            return false;
        }
        messageRepository.updateMessageContentById(messageId, message.getMessageText());
        return true;
    }

    public List<Message> getMessagesByAccountId(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}
