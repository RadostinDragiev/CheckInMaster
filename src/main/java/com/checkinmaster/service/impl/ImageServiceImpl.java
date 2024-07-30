package com.checkinmaster.service.impl;

import com.checkinmaster.model.entity.Image;
import com.checkinmaster.model.entity.Room;
import com.checkinmaster.repository.ImageRepository;
import com.checkinmaster.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;


    @Override
    public void saveImage(String assetId, String publicId, String url, LocalDateTime createdDateTime, Room room) {
        Image image = Image.builder()
                .assetId(assetId)
                .publicId(publicId)
                .url(url)
                .createdDateTime(createdDateTime)
                .room(room).build();

        this.imageRepository.saveAndFlush(image);
    }
}
