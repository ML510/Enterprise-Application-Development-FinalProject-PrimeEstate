package com.realestate.management.controller;

import com.realestate.management.Dto.ViewingDto;
import com.realestate.management.service.ViewingService;
import com.realestate.management.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viewings")
@RequiredArgsConstructor
public class ViewingController {

    private final ViewingService viewingService;

    @PostMapping("/add")
    public ApiResponse addViewing(@RequestBody ViewingDto viewingDto) {
        System.out.println("Received viewing request: ");;
        return viewingService.addViewing(viewingDto);
    }

    @GetMapping("/get")
    public ApiResponse get(){
        return viewingService.get();
    }

    @GetMapping("/get-last-two")
    public ApiResponse getLastTwo(){
        return viewingService.getLastTwo();
    }
}
