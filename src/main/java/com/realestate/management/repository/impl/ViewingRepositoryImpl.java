package com.realestate.management.repository.impl;

import com.realestate.management.entity.ViewingEntity;
import com.realestate.management.repository.ViewingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ViewingRepositoryImpl implements ViewingRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Boolean addViewing(ViewingEntity viewingEntity) {
        return jdbcTemplate.update(
                "INSERT INTO viewing (name, email, phone, date, time, note) VALUES (?, ?, ?, ?, ?, ?)",
                viewingEntity.getName(),
                viewingEntity.getEmail(),
                viewingEntity.getPhone(),
                viewingEntity.getViewingDate(),
                viewingEntity.getTime(),
                viewingEntity.getNote()
        ) > 0;
    }

    @Override
    public List<ViewingEntity> get() {
        List<ViewingEntity> list = jdbcTemplate.query("SELECT  `name`,  `email`,  `phone`,  `date`,  `time`,  `note` FROM `viewing` ",
                (rs, rowNum) -> new ViewingEntity(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDate("date"),
                        rs.getTime("time"),
                        rs.getString("note")
                ));
        return list;
    }

    @Override
    public List<ViewingEntity> getLastTwo() {
        List<ViewingEntity> list = jdbcTemplate.query("SELECT `name`, `email`, `phone`, `date`, `time`, `note` FROM `viewing` ORDER BY `id` DESC LIMIT 4",
                (rs, rowNum) -> new ViewingEntity(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDate("date"),
                        rs.getTime("time"),
                        rs.getString("note")
                ));
        return list;
    }
}
