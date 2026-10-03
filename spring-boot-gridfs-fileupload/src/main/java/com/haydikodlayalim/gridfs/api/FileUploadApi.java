package com.haydikodlayalim.gridfs.api;

import com.haydikodlayalim.gridfs.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileUploadApi {

    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String fileId = fileUploadService.uploadFile(file);
        return ResponseEntity.ok(fileId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> download(@PathVariable String id) throws IOException {
        GridFsResource resource = fileUploadService.downloadFile(id);
        if (resource == null || !resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(resource.getContentType() != null ? resource.getContentType() : MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        fileUploadService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
