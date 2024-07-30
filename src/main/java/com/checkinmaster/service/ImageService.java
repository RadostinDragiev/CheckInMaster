package com.checkinmaster.service;

import com.checkinmaster.model.entity.Room;

import java.time.LocalDateTime;

public interface ImageService {

    void saveImage(String assetId, String publicId, String url, LocalDateTime createdDateTime, Room room);
}
