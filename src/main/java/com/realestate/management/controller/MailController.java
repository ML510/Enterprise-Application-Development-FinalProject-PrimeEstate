package com.realestate.management.controller;

import com.realestate.management.service.MailService;
import com.realestate.management.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/subscribe")
    public ApiResponse subscribe(@RequestParam String email) {
        return mailService.subscribe(email);
    }

}
