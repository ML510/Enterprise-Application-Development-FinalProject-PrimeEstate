package com.realestate.management.repository.impl;

import com.realestate.management.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MailRepositoryImpl implements MailRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Boolean subscribe(String email) {
        return jdbcTemplate.update("INSERT INTO subscribers (email) VALUES (?)", email) > 0;
    }
}
