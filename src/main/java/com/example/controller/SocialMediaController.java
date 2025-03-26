package com.example.controller;

import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.Account;
import com.example.entity.Message;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use
 * the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations.
 * You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */
@Controller
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {

        return accountService.createAccount(account);

    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {

        return accountService.login(account);

    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {

        return messageService.createMessage(message);

    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {

        return messageService.getAllMessages();

    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageByMessageId(@PathVariable Integer messageId) {

        return messageService.getMessageByMessageId(messageId);

    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer messageId) {

        return messageService.deleteMessage(messageId);

    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> patchMessage(@RequestBody Message message, @PathVariable Integer messageId) {

        return messageService.patchMessage(message, messageId);

    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByAccountId(@PathVariable Integer accountId) {

        return messageService.getMessagesByAccountId(accountId);

    }

}
