package com.example.cleancodefileupload.controller;


import com.example.cleancodefileupload.entity.FileStorage;
import com.example.cleancodefileupload.service.FileStorageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/file")
public class FileStorageController {

    private final FileStorageService fileStorageService;

    @Value("${upload.folder}")
    private  String uploadFolder ;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file")MultipartFile multipartFile) {
        fileStorageService.save(multipartFile);
    return ResponseEntity.ok(multipartFile.getOriginalFilename() + "File saqlandi...");

    }

    @GetMapping("/preview/{hashId}")
    public  ResponseEntity previewFile(@PathVariable String hashId) throws IOException  {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName\"" + URLEncoder.encode(fileStorage.getName()))
                .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .contentLength(fileStorage.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s", uploadFolder,fileStorage.getUploadPath())));
    }
    @GetMapping("/download/{hashId}")
    public  ResponseEntity downloadFile(@PathVariable String hashId) throws IOException  {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName\"" + URLEncoder.encode(fileStorage.getName()))
                .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .contentLength(fileStorage.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s", uploadFolder,fileStorage.getUploadPath())));
    }

    @DeleteMapping("/delete/{hashId}")
    public  ResponseEntity delete(@PathVariable String hashId) {
        fileStorageService.delete(hashId);
        return  ResponseEntity.ok("File o'chirildi");
    }



}
