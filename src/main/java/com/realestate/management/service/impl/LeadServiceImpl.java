package com.realestate.management.service.impl;

import com.realestate.management.Dto.LeadDto;
import com.realestate.management.entity.LeadEntity;
import com.realestate.management.repository.LeadRepository;
import com.realestate.management.service.LeadService;
import com.realestate.management.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {

    final ModelMapper mapper;
    final LeadRepository leadRepository;

    @Override
    public ApiResponse addLead(LeadDto leadDto) {
        Integer rowCount = leadRepository.addLead(mapper.map(leadDto, LeadEntity.class));
        return new ApiResponse(rowCount > 0, rowCount > 0 ? "Lead added successfully" : "Failed to add lead", null, LocalDateTime.now());
    }

    @Override
    public ApiResponse getLeads() {
        List<LeadEntity> leadEntities = leadRepository.getLeads();
        List<LeadDto> leadDtos = leadEntities.stream().map(leadEntity -> mapper.map(leadEntity, LeadDto.class)).toList();
        return new ApiResponse(!leadEntities.isEmpty(), !leadEntities.isEmpty()  ? "Leads Load successsfully":" Faild to load leads or no leads found", leadDtos, LocalDateTime.now());
    }

    @Override
    public ApiResponse deleteLeadById(Integer id) {
        Integer rowCount = leadRepository.deleteLeadById(id);
        return new ApiResponse(rowCount > 0, rowCount > 0 ? "Lead deleted successfully" : "Failed to delete lead", null, LocalDateTime.now());
    }

    @Override
    public ApiResponse updateLeadById(LeadDto leadDto) {
        Integer rowCount = leadRepository.updateLeadById(mapper.map(leadDto, LeadEntity.class));
        return new ApiResponse(rowCount > 0, rowCount > 0 ? "Lead updated successfully" : "Failed to update lead", null, LocalDateTime.now());
    }

    @Override
    public ApiResponse getLeadCount() {
        return new ApiResponse<>(true, "Lead count retrieved successfully", leadRepository.getLeadCount(), LocalDateTime.now());
    }
}
