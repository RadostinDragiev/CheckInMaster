package com.checkinmaster.web;

import com.checkinmaster.util.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<Void> uploadImage(@RequestParam("file") List<MultipartFile> file) throws IOException {
        this.cloudinaryService.uploadFiles(file, null);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteImage(@RequestParam("publicIds") String publicIds) throws Exception {
        this.cloudinaryService.deleteFiles(List.of(publicIds));
        return ResponseEntity.ok().build();
    }
}
