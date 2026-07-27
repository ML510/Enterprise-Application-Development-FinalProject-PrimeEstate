package com.realestate.management.service.impl;

import com.realestate.management.Dto.ViewingDto;
import com.realestate.management.entity.ViewingEntity;
import com.realestate.management.repository.ViewingRepository;
import com.realestate.management.service.ViewingService;
import com.realestate.management.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ViewingServiceImpl implements ViewingService {

    private final ViewingRepository viewingRepository;
    private final ModelMapper mapper;

    @Override
    public ApiResponse addViewing(ViewingDto viewingDto) {
        Boolean isAdded = viewingRepository.addViewing(mapper.map(viewingDto, ViewingEntity.class));
        return new ApiResponse<>(isAdded,isAdded ? "added Success ":" adding faild",null, LocalDateTime.now());
    }

    @Override
    public ApiResponse get() {
        List<ViewingEntity> viewingEntities = viewingRepository.get();
        List<ViewingDto> viewingDtos = viewingEntities.stream().map(viewingEntity -> mapper.map(viewingEntity, ViewingDto.class)).toList();
        return new ApiResponse<>(true,"get Success ",viewingDtos, LocalDateTime.now());
    }

    @Override
    public ApiResponse getLastTwo() {
        List<ViewingEntity> viewingEntities = viewingRepository.getLastTwo();
        List<ViewingDto> viewingDtos = viewingEntities.stream().map(viewingEntity -> mapper.map(viewingEntity, ViewingDto.class)).toList();
        return new ApiResponse<>(true,"get Success ",viewingDtos, LocalDateTime.now());
    }
}
