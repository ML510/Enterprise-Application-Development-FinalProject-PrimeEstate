package com.realestate.management.repository;

import com.realestate.management.entity.LeadEntity;

import java.util.List;

public interface LeadRepository {

    Integer updateLeadById(LeadEntity map);

    Integer deleteLeadById(Integer id);

    List<LeadEntity> getLeads();

    Integer addLead(LeadEntity map);

    Integer getLeadCount();
}
