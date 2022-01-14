package com.example.cleancodefileupload.repository;

import com.example.cleancodefileupload.controller.FileStorageStatus;
import com.example.cleancodefileupload.entity.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {

 FileStorage findByHashId(String hashId);

 List<FileStorage> findAllByFileStorageStatus(FileStorageStatus status);

}
