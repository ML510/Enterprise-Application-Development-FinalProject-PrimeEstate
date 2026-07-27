package com.realestate.management.repository;

import com.realestate.management.entity.ViewingEntity;

import java.util.List;

public interface ViewingRepository {
    Boolean addViewing(ViewingEntity viewingEntity);

    List<ViewingEntity> get();

    List<ViewingEntity> getLastTwo();
}
