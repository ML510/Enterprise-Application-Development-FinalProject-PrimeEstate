package com.realestate.management.repository.impl;

import com.realestate.management.entity.PropertyEntity;
import com.realestate.management.enums.PropertyStatus;
import com.realestate.management.enums.State;
import com.realestate.management.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PropertyRepositoryImpl implements PropertyRepository {

    final JdbcTemplate jdbcTemplate;

    @Override
    public PropertyEntity add(PropertyEntity propertyEntity) {
        jdbcTemplate.update("INSERT INTO property (name, state, price, max_bed_count, min_bed_count, sqft, dsc,url,status) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)",
                propertyEntity.getName(),
                propertyEntity.getState().toString(),
                propertyEntity.getPrice(),
                propertyEntity.getMaxBedCount(),
                propertyEntity.getMinBedCount(),
                propertyEntity.getSqft(),
                propertyEntity.getDsc(),
                propertyEntity.getUrl(),
                propertyEntity.getStatus().toString()
        );
        return propertyEntity;
    }

    @Override
    public List<PropertyEntity> getAll() {
        List<PropertyEntity> list = jdbcTemplate.query("SELECT  `id`,  `name`,  `state`,  `price`,  `max_bed_count`,  `min_bed_count`,  `sqft`,`dsc`,`url`,`status` FROM property ",
                (rs, rowNum) -> new PropertyEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        State.valueOf(rs.getString(3)),
                        rs.getDouble(4),
                        rs.getString(9),
                        PropertyStatus.valueOf(rs.getString("status")),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8)
                ));
        return list;
    }

    @Override
    public List<PropertyEntity> getByStatus(PropertyStatus status) {
        List<PropertyEntity> list = jdbcTemplate.query("SELECT  `id`,  `name`,  `state`,  `price`,  `max_bed_count`,  `min_bed_count`,  `sqft`,`dsc`,`url`,`status` FROM property WHERE `status` = ?",
                (rs, rowNum) -> new PropertyEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        State.valueOf(rs.getString(3)),
                        rs.getDouble(4),
                        rs.getString(9),
                        PropertyStatus.valueOf(rs.getString("status")),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8)
                ),status.toString());
        return list;
    }

    @Override
    public List<PropertyEntity> getBySearch(String name, String state, Double price) {
        StringBuilder sql = new StringBuilder("SELECT * FROM property WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name + "%");
        }

        if (state != null && !state.trim().isEmpty()) {
            sql.append(" AND state = ?");
            params.add(state);
        }

        if (price != null) {
            sql.append(" AND price <= ?");
            params.add(price);
        }

        return jdbcTemplate.query(
                sql.toString(),
                params.toArray(),
                new BeanPropertyRowMapper<>(PropertyEntity.class)
        );
    }

    @Override
    public PropertyEntity getById(Integer id) {
            return jdbcTemplate.queryForObject("SELECT  `id`,  `name`,  `state`,  `price`,  `max_bed_count`,  `min_bed_count`,  `sqft`,`dsc`,`url`,`status` FROM property WHERE id = ?",
                    (rs, rowNum) -> new PropertyEntity(
                            rs.getInt(1),
                            rs.getString(2),
                            State.valueOf(rs.getString(3)),
                            rs.getDouble(4),
                            rs.getString(9),
                            PropertyStatus.valueOf(rs.getString("status")),
                            rs.getInt(5),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getString(8)
                    ),id);
    }

    @Override
    public List<PropertyEntity> getRelatedCollection() {
        return jdbcTemplate.query(
                "SELECT `id`, `name`, `state`, `price`, `max_bed_count`, `min_bed_count`, `sqft`, `dsc`, `url`, `status` " +
                        "FROM property " +
                        "ORDER BY RAND() " +
                        "LIMIT 3",
                (rs, rowNum) -> new PropertyEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        State.valueOf(rs.getString(3)),
                        rs.getDouble(4),
                        rs.getString(9),
                        PropertyStatus.valueOf(rs.getString("status")),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8)
                )
        );
    }

    @Override
    public Integer getCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM property", Integer.class);
    }

    @Override
    public List<PropertyEntity> get(Integer page, Integer size, String sort, String direction) {
        String sortColumn = switch (sort == null ? "" : sort.toLowerCase()) {
            case "name" -> "name";
            case "state" -> "state";
            case "price" -> "price";
            case "status" -> "status";
            case "sqft" -> "sqft";
            case "id" -> "id";
            default -> "id";
        };

        String sortDir = "desc".equalsIgnoreCase(direction) ? "DESC" : "ASC";
        int offset = Math.max(page - 1, 0) * size;

        String sql = "SELECT `id`, `name`, `state`, `price`, `max_bed_count`, `min_bed_count`, `sqft`, `dsc`, `url`, `status` " +
                "FROM property " +
                "ORDER BY " + sortColumn + " " + sortDir + " " +
                "LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new PropertyEntity(
                rs.getInt("id"),
                rs.getString("name"),
                State.valueOf(rs.getString("state")),
                rs.getDouble("price"),
                rs.getString("url"),
                PropertyStatus.valueOf(rs.getString("status")),
                rs.getInt("max_bed_count"),
                rs.getInt("min_bed_count"),
                rs.getInt("sqft"),
                rs.getString("dsc")
        ), size, offset);
    }



}
