package com.checkinmaster.util.impl;

import com.checkinmaster.util.CloudinaryService;
import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Override
    public void uploadFile(List<MultipartFile> file, int roomNumber) throws IOException {
        Uploader uploader = this.cloudinary.uploader();
        Map<String, Object> map = new HashMap<>();
        int roomId = 2;
        map.put("folder", "check-in-master/room" + roomId);
        map.put("public_id", roomId + "-" + "picture");

        List<File> convFiles = convertToFile(file);
        Map upload = uploader.upload(convFiles, map);
        log.info("File uploaded with public_id" + upload.get("public_id"));
        log.info("File uploaded with asset_id" + upload.get("asset_id"));
        System.out.println();
    }

    @Override
    public void fetchFile(List<String> assetIds) throws Exception {
        Api api = this.cloudinary.api();

        ApiResponse file = api.resourcesByAssetIDs(assetIds, ObjectUtils.emptyMap());
    }

    @Override
    public void deleteFile(List<String> assetId) throws Exception {
        Api api = this.cloudinary.api();
        ApiResponse invalidate = api.deleteResources(assetId, ObjectUtils.asMap("invalidate", true));
        Map<String, String> map = (Map<String, String>) invalidate.get("deleted");
        map.forEach((key, value) -> log.warn(String.format("For public id: %s response is %s", key, value)));
    }

    public List<File> convertToFile(List<MultipartFile> files) {
        return files.stream()
                .map(mf -> {
                    File file = new File(mf.getOriginalFilename());
                    try (OutputStream os = new FileOutputStream(file)) {
                        os.write(mf.getBytes());
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return file;
                })
                .toList();
    }
}
