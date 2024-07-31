package com.checkinmaster.util.impl;

import com.checkinmaster.model.entity.Room;
import com.checkinmaster.service.ImageService;
import com.checkinmaster.util.CloudinaryService;
import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String LOCAL_STORAGE_PATH = "src/main/resources/temp/";
    private static final String CLOUDINARY_FOLDER_PREFIX = "check-in-master/room-";
    private static final String CLOUDINARY_IMAGE_PREFIX = "picture-";

    private final Cloudinary cloudinary;
    private final ImageService imageService;

    @Override
    public void uploadFiles(List<MultipartFile> multipartFiles, Room room) {

        int filesCount = 1;
        for (MultipartFile file : multipartFiles) {
            if (multipartFiles.isEmpty()) {
                log.info("------ Failed to upload " + file.getOriginalFilename() + "because it was empty.");
                continue;
            }

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(LOCAL_STORAGE_PATH + file.getOriginalFilename());
                Files.write(path, bytes);

                File uploadedFile = path.toFile();
                Map<String, Object> map = new HashMap<>();
                map.put("folder", CLOUDINARY_FOLDER_PREFIX + room.getNumber());
                map.put("public_id", CLOUDINARY_IMAGE_PREFIX + filesCount++);
                Map<String, String> uploadResult = cloudinary.uploader().upload(uploadedFile, map);

                this.imageService.saveImage(uploadResult.get("asset_id"), uploadResult.get("public_id"),
                        uploadResult.get("secure_url"), LocalDateTime.now(), room);

                log.info("----- Successfully uploaded " + file.getOriginalFilename()
                        + " with Cloudinary asset_id: " + uploadResult.get("asset_id")
                        + " and with Cloudinary public_id: " + uploadResult.get("public_id"));

                if (uploadedFile.delete()) {
                    log.info("----- File " + file.getOriginalFilename() + " deleted successfully. ");
                } else {
                    log.error("----- Failed to delete file " + file.getOriginalFilename());
                }
            } catch (IOException e) {
                log.error("----- Failed to upload " + file.getOriginalFilename() + " due to an error. ");
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public void fetchFiles(List<String> assetIds) throws Exception {
        Api api = this.cloudinary.api();

        ApiResponse file = api.resourcesByAssetIDs(assetIds, ObjectUtils.emptyMap());
    }

    @Override
    public void deleteFiles(List<String> publicIds) throws Exception {
        ApiResponse invalidate = this.cloudinary.api()
                        .deleteResources(publicIds, ObjectUtils.asMap("invalidate", true));

        Map<String, String> map = (Map<String, String>) invalidate.get("deleted");
        map.forEach((key, value) -> log.warn(String.format("For public id: %s response is %s", key, value)));
    }
}
