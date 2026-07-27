package com.realestate.management.controller;

import com.realestate.management.Dto.LeadDto;
import com.realestate.management.service.LeadService;
import com.realestate.management.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lead")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;

    @PostMapping("/add")
    public ApiResponse addLead(@RequestBody LeadDto leadDto) {
        return leadService.addLead(leadDto);
    }

    @GetMapping("/get")
    public ApiResponse getLeads() {
        return leadService.getLeads();
    }

    @PutMapping("/update-by-id")
    public ApiResponse updateLeadById(@RequestBody LeadDto leadDto) {
        return leadService.updateLeadById(leadDto);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ApiResponse deleteLeadById(@PathVariable Integer id) {
        return leadService.deleteLeadById(id);
    }

    @GetMapping("/get-count")
    public ApiResponse getLeadCount() {
        return leadService.getLeadCount();
    }

}
