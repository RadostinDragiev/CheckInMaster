package com.checkinmaster.service.impl;

import com.checkinmaster.model.entity.Image;
import com.checkinmaster.repository.ImageRepository;
import com.checkinmaster.service.ImageService;
import com.checkinmaster.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final RoomService roomService;


    @Override
    public void saveImage(String assetId, String publicId, String url, LocalDateTime createdDateTime, UUID roomId) {
        Image image = Image.builder()
                .assetId(assetId)
                .publicId(publicId)
                .url(url)
                .createdDateTime(createdDateTime)
                .room(this.roomService.getRoomById(roomId)).build();

        this.imageRepository.saveAndFlush(image);
    }
}
