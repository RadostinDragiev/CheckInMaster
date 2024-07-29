package com.checkinmaster.util.impl;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String LOCAL_STORAGE_PATH = "src/main/resources/temp/";

    private final Cloudinary cloudinary;

    @Override
    public void uploadFile(List<MultipartFile> multipartFiles, int roomNumber) {

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
                map.put("folder", "check-in-master/room-" + roomNumber);
                map.put("public_id", "picture-" + filesCount++);
                Map uploadResult = cloudinary.uploader().upload(uploadedFile, map);

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
    public void fetchFile(List<String> assetIds) throws Exception {
        Api api = this.cloudinary.api();

        ApiResponse file = api.resourcesByAssetIDs(assetIds, ObjectUtils.emptyMap());
    }

    @Override
    public void deleteFile(List<String> publicIds) throws Exception {
        ApiResponse invalidate = this.cloudinary.api().
                deleteResources(publicIds, ObjectUtils.asMap("invalidate", true));

        Map<String, String> map = (Map<String, String>) invalidate.get("deleted");
        map.forEach((key, value) -> log.warn(String.format("For public id: %s response is %s", key, value)));
    }
}
