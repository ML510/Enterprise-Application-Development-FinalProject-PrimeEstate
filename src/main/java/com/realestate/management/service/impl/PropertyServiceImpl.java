package com.realestate.management.service.impl;

import com.realestate.management.Dto.PropertyDto;
import com.realestate.management.entity.PropertyEntity;
import com.realestate.management.enums.PropertyStatus;
import com.realestate.management.repository.PropertyRepository;
import com.realestate.management.service.PropertyService;
import com.realestate.management.util.ApiResponse;
import com.realestate.management.util.Page;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    final ModelMapper mapper;
    final PropertyRepository propertyRepository;

    @Override
    public ApiResponse add(PropertyDto propertyDto) {
        PropertyDto propertyDto1 = mapper.map(propertyRepository.add(mapper.map(propertyDto, PropertyEntity.class)), PropertyDto.class);
        return new ApiResponse(true,"All good",propertyDto1, LocalDateTime.now());
    }

    public ApiResponse getAll(){
        List<PropertyEntity> propertyEntities = propertyRepository.getAll();
        List<PropertyDto> propertyDtoList = new ArrayList<>();

        propertyEntities.forEach(propertyEntity -> {
            propertyDtoList.add(mapper.map(propertyEntity, PropertyDto.class));
        });

        return new ApiResponse<>(true,"All good",propertyDtoList,LocalDateTime.now());
    }

    @Override
    public ApiResponse getByStatus(PropertyStatus status) {
        List<PropertyEntity> propertyEntities = propertyRepository.getByStatus(status);
        List<PropertyDto> propertyDtoList = new ArrayList<>();

        propertyEntities.forEach(propertyEntity -> {
            propertyDtoList.add(mapper.map(propertyEntity, PropertyDto.class));
        });

        return new ApiResponse<>(true,"All good",propertyDtoList,LocalDateTime.now());
    }

    @Override
    public ApiResponse getBySearch(String name, String state, Double price) {
        List<PropertyEntity> propertyEntities = propertyRepository.getBySearch(name,state,price);
        List<PropertyDto> propertyDtoList = new ArrayList<>();

        propertyEntities.forEach(propertyEntity -> {
            propertyDtoList.add(mapper.map(propertyEntity, PropertyDto.class));
        });

        return new ApiResponse<>(true,"All good",propertyDtoList,LocalDateTime.now());
    }

    @Override
    public ApiResponse getById(Integer id) {
        return new ApiResponse<>(true,"",mapper.map(propertyRepository.getById(id),PropertyDto.class),LocalDateTime.now());
    }

    @Override
    public ApiResponse getRelatedCollection() {
        List<PropertyEntity> propertyEntities = propertyRepository.getRelatedCollection();
        List<PropertyDto> propertyDtos = new ArrayList<>();
        propertyEntities.forEach(propertyEntity -> {
            propertyDtos.add(mapper.map(propertyEntity,PropertyDto.class));
        });
        return new ApiResponse<>(propertyDtos.isEmpty() ? false : true,"",propertyDtos,LocalDateTime.now());
    }

    @Override
    public ApiResponse getCount() {
        return new ApiResponse<>(true,"",propertyRepository.getCount(),LocalDateTime.now());
    }

    @Override
    public ApiResponse<Page<List<PropertyDto>>> get(Integer page, Integer size, String sort, String direction) {
        List<PropertyEntity> propertyEntities = propertyRepository.get(page,size,sort,direction);

        List<PropertyDto> propertyDtos = new ArrayList<>();
        propertyEntities.forEach(propertyEntity -> {
            propertyDtos.add(mapper.map(propertyEntity,PropertyDto.class));
        });

        Page<List<PropertyDto>> propertyDtoPage = new Page<>(page,size,sort,direction,propertyRepository.getCount(),(int) Math.ceil((double) propertyRepository.getCount() / size),propertyDtos.size() < size,propertyDtos);

        return new ApiResponse<Page<List<PropertyDto>>>(propertyDtos.isEmpty(),"",propertyDtoPage,LocalDateTime.now());
    }


}
