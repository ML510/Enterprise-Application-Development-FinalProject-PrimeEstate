package com.realestate.management.service;

import com.realestate.management.Dto.ViewingDto;
import com.realestate.management.util.ApiResponse;

public interface ViewingService {
    ApiResponse addViewing(ViewingDto viewingDto);

    ApiResponse get();

    ApiResponse getLastTwo();
}
