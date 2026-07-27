package com.realestate.management.service;

import com.realestate.management.Dto.PropertyDto;
import com.realestate.management.enums.PropertyStatus;
import com.realestate.management.util.ApiResponse;
import com.realestate.management.util.Page;

import java.util.List;

public interface PropertyService {
    ApiResponse add(PropertyDto propertyDto);

    ApiResponse getAll();

    ApiResponse getByStatus(PropertyStatus status);

    ApiResponse getBySearch(String name, String state, Double price);

    ApiResponse getById(Integer id);

    ApiResponse getRelatedCollection();

    ApiResponse getCount();

    ApiResponse<Page<List<PropertyDto>>> get(Integer page, Integer size, String sort, String direction);
}
