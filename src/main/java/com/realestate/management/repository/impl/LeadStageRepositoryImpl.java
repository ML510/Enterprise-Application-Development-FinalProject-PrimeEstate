package com.realestate.management.repository.impl;

import com.realestate.management.entity.LeadStageEntity;
import com.realestate.management.repository.LeadStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LeadStageRepositoryImpl implements LeadStageRepository {

    final private JdbcTemplate jdbcTemplate;

    @Override
    public Integer updateLeadStage(LeadStageEntity leadStageEntity) {
        return jdbcTemplate.update(
                "UPDATE lead_stage SET name = ?, description = ? WHERE id = ?",
                leadStageEntity.getName(), leadStageEntity.getDescription(), leadStageEntity.getId()
        );
    }

    @Override
    public Integer addLeadStage(LeadStageEntity leadStageEntity) {
        return jdbcTemplate.update(
                "INSERT INTO lead_stage (name, description,createdDate) VALUES (?, ?,?)",
                leadStageEntity.getName(), leadStageEntity.getDescription(), LocalDateTime.now()
        );
    }

    @Override
    public List<LeadStageEntity> getAllLeadStages() {
        return jdbcTemplate.query(
                "SELECT id, name,createdDate, description FROM lead_stage",
                (rs, rowNum) -> new LeadStageEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("createdDate"),
                        rs.getString("description")
                )
        );
    }

    @Override
    public List<LeadStageEntity> getByName(String name) {
        return jdbcTemplate.query(
                "SELECT id, name,createdDate, description FROM lead_stage where name ='?'",
                (rs, rowNum) -> new LeadStageEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("createdDate"),
                        rs.getString("description")
                )
        );
    }
}
