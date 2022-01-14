package com.example.cleancodefileupload.entity;


import com.example.cleancodefileupload.controller.FileStorageStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class FileStorage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    private String extension;

    private  Long fileSize;

    private  String hashId;

    private  String contentType;

    private  String uploadPath;

    @Enumerated(EnumType.STRING )
    private FileStorageStatus fileStorageStatus;

}
