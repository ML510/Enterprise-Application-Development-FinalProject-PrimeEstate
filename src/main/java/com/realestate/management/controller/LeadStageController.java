package com.realestate.management.controller;

import com.realestate.management.Dto.LeadStageDto;
import com.realestate.management.service.LeadStageService;
import com.realestate.management.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lead-stages")
@RequiredArgsConstructor
public class LeadStageController {

    final private LeadStageService leadStageService;

    @PostMapping("/add")
    public ApiResponse addLeadStage(@RequestBody LeadStageDto leadStageDto) {
        return leadStageService.addLeadStage(leadStageDto);
    }

    @PutMapping("/update")
    public ApiResponse updateLeadStage(@RequestBody LeadStageDto leadStageDto) {
        return leadStageService.updateLeadStage(leadStageDto);
    }

    @GetMapping("getAll")
    public ApiResponse getAllLeadStages() {
        return leadStageService.getAllLeadStages();
    }

    @GetMapping("/get-by-name")
    public ApiResponse getLeadStageByName(@RequestParam String name) {
        return leadStageService.getLeadStageByName(name);
    }

}
