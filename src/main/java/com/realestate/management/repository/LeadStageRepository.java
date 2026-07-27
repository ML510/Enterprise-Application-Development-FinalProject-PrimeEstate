package com.realestate.management.repository;

import com.realestate.management.entity.LeadStageEntity;

import java.util.List;

public interface LeadStageRepository {
    Integer updateLeadStage(LeadStageEntity map);

    Integer addLeadStage(LeadStageEntity map);

    List<LeadStageEntity> getAllLeadStages();

    List<LeadStageEntity> getByName(String name);
}
