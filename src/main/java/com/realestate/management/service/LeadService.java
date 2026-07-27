package com.realestate.management.service;

import com.realestate.management.Dto.LeadDto;
import com.realestate.management.util.ApiResponse;

public interface LeadService {
    ApiResponse addLead(LeadDto leadDto);

    ApiResponse getLeads();

    ApiResponse deleteLeadById(Integer id);

    ApiResponse updateLeadById(LeadDto leadDto);

    ApiResponse getLeadCount();
}
