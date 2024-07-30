package com.checkinmaster.service;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ImageService {

    void saveImage(String assetId, String publicId, String url, LocalDateTime createdDateTime, UUID roomId);
}
