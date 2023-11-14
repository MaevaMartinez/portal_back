package com.elitsoft.proyectoCuestionario_backend.servicios;


import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public interface FileService {


    public void init();
    public void save(MultipartFile file) throws IOException;
    public Resource load(String filename) throws IOException;
    public void deleteall();
    public Stream<Path> loadAll(Long usr_id);
    public String deletefile(String filename);


    public void deleteFile(String filePath) throws IOException;


    String saveFile(MultipartFile file) throws IOException;
    Resource getCV(String fileName) throws IOException;





}
