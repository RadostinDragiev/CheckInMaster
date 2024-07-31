package com.checkinmaster.service.impl;

import com.checkinmaster.model.entity.Image;
import com.checkinmaster.model.entity.Room;
import com.checkinmaster.repository.ImageRepository;
import com.checkinmaster.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @Override
    public List<String> getImagePublicIds(UUID roomUUID) {
        return this.imageRepository.findAllByRoom_Uuid(roomUUID)
                .stream()
                .map(Image::getPublicId)
                .toList();
    }
}
