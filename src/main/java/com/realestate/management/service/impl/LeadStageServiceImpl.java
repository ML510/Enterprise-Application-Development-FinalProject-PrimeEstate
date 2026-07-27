package com.realestate.management.service.impl;

import com.realestate.management.Dto.LeadStageDto;
import com.realestate.management.entity.LeadStageEntity;
import com.realestate.management.repository.LeadStageRepository;
import com.realestate.management.service.LeadStageService;
import com.realestate.management.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadStageServiceImpl implements LeadStageService {

    private final LeadStageRepository leadStageRepository;
    private final ModelMapper mapper;

    @Override
    public ApiResponse addLeadStage(LeadStageDto leadStageDto) {
        Integer rowCount = leadStageRepository.addLeadStage(mapper.map(leadStageDto, LeadStageEntity.class));
        return new ApiResponse(rowCount > 0, rowCount > 0 ? "Lead stage added successfully" : "Failed to add lead stage",null, LocalDateTime.now());
    }

    @Override
    public ApiResponse updateLeadStage(LeadStageDto leadStageDto) {
        Integer rowCount = leadStageRepository.updateLeadStage(mapper.map(leadStageDto, LeadStageEntity.class));
        return new ApiResponse(rowCount > 0, rowCount > 0 ? "Lead stage updated successfully" : "Failed to update lead stage",null, LocalDateTime.now());
    }

    @Override
    public ApiResponse getAllLeadStages() {
        List<LeadStageEntity> leadStageEntities = leadStageRepository.getAllLeadStages();
        List<LeadStageDto> leadStageDtos = leadStageEntities.stream().map(leadStageEntity -> mapper.map(leadStageEntity, LeadStageDto.class)).toList();
        return new ApiResponse(!leadStageEntities.isEmpty(), !leadStageEntities.isEmpty() ? "Lead stages loaded successfully" : "Failed to load lead stages or no lead stages found", leadStageDtos, LocalDateTime.now());
    }

    @Override
    public ApiResponse getLeadStageByName(String name) {
        List<LeadStageEntity> leadStageEntities = leadStageRepository.getByName(name);
        List<LeadStageDto> leadStageDtos = leadStageEntities.stream().map(leadStageEntity -> mapper.map(leadStageEntity, LeadStageDto.class)).toList();
        return new ApiResponse(!leadStageEntities.isEmpty(), !leadStageEntities.isEmpty() ? "Lead stages loaded successfully" : "Failed to load lead stages or no lead stages found", leadStageDtos, LocalDateTime.now());
    }
}
