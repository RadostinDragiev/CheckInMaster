package com.checkinmaster.util;

import com.checkinmaster.model.entity.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CloudinaryService {

    void uploadFiles(List<MultipartFile> file, Room room) throws IOException;

    void fetchFiles(List<String> assetIds) throws Exception;

    void deleteFiles(List<String> publicIds) throws Exception;
}
