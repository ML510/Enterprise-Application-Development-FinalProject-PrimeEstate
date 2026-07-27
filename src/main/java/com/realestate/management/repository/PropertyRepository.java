package com.realestate.management.repository;

import com.realestate.management.entity.PropertyEntity;
import com.realestate.management.enums.PropertyStatus;

import java.util.List;

public interface PropertyRepository {
    PropertyEntity add(PropertyEntity map);

    List<PropertyEntity> getAll();

    List<PropertyEntity> getByStatus(PropertyStatus status);

    List<PropertyEntity> getBySearch(String name, String state, Double price);

    PropertyEntity getById(Integer id);

    List<PropertyEntity> getRelatedCollection();

    Integer getCount();

    List<PropertyEntity> get(Integer page, Integer size, String sort, String direction);
}
