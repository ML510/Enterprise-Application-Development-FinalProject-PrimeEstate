package com.realestate.management.controller;

import com.realestate.management.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/model")
@RequiredArgsConstructor
public class ModelController {

    final ModelService modelService;

    @GetMapping("/send")
    public String sendToModel(@RequestParam String msg) {
        return modelService.send(msg);
    }

    @GetMapping("/enhance")
    public String enhanceDescription(@RequestParam String description) {
        return modelService.enhanceDescription(description);
    }
}
