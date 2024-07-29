package com.checkinmaster.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CloudinaryService {

    void uploadFile(List<MultipartFile> file, int roomNumber) throws IOException;

    void fetchFile(List<String> assetIds) throws Exception;

    void deleteFile(List<String> publicIds) throws Exception;
}
