package com.checkinmaster.service;

import com.checkinmaster.model.entity.Room;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ImageService {

    void saveImage(String assetId, String publicId, String url, LocalDateTime createdDateTime, Room room);

    List<String> getImagePublicIds(UUID roomUUID);
}
