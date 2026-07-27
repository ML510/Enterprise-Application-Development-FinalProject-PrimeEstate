package com.realestate.management.repository.impl;

import com.realestate.management.entity.LeadEntity;
import com.realestate.management.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LeadRepositoryImpl implements LeadRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer updateLeadById(LeadEntity leadEntity) {
        return jdbcTemplate.update("UPDATE leads SET name = ?, email = ?, phone = ?, last_contact = ?, stage = ?, profile_pic = ? WHERE id = ?",
                leadEntity.getName(),
                leadEntity.getEmail(),
                leadEntity.getPhone(),
                toTimestamp(leadEntity.getLastContact()),
                leadEntity.getStage(),
                leadEntity.getProfilePic(),
                leadEntity.getId()
        );
    }

    @Override
    public Integer deleteLeadById(Integer id) {
        return jdbcTemplate.update("DELETE from leads where id = ?",id);
    }

    @Override
    public List<LeadEntity> getLeads() {
        return jdbcTemplate.query("SELECT id, name, email, phone, last_contact, stage, profile_pic FROM leads",
                (rs, rowNum) -> new LeadEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTimestamp(5) != null ? rs.getTimestamp(5).toLocalDateTime() : null,
                        rs.getString(6),
                        rs.getString(7)
                ));
    }

    @Override
    public Integer addLead(LeadEntity lead) {
        return jdbcTemplate.update("INSERT INTO leads (name,email,phone,last_contact,stage,profile_pic) VALUES (?,?,?,?,?,?)",
                lead.getName(),
                lead.getEmail(),
                lead.getPhone(),
                toTimestamp(lead.getLastContact()),
                lead.getStage(),
                lead.getProfilePic()
        );
    }

    @Override
    public Integer getLeadCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM leads", Integer.class);
    }

    private Timestamp toTimestamp(java.time.LocalDateTime value) {
        return value == null ? null : Timestamp.valueOf(value);
    }
}
