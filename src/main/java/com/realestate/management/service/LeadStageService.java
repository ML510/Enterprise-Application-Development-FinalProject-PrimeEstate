package com.realestate.management.service;

import com.realestate.management.Dto.LeadStageDto;
import com.realestate.management.util.ApiResponse;

public interface LeadStageService {
    ApiResponse addLeadStage(LeadStageDto leadStageDto);

    ApiResponse updateLeadStage(LeadStageDto leadStageDto);

    ApiResponse getAllLeadStages();

    ApiResponse getLeadStageByName(String name);
}
