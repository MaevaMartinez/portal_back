package com.elitsoft.proyectoCuestionario_backend.controladores;

import com.elitsoft.proyectoCuestionario_backend.message.FileMessage;
import com.elitsoft.proyectoCuestionario_backend.model.FileModel;
import com.elitsoft.proyectoCuestionario_backend.servicios.FileService;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("*")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileMessage> uploadFiles(@RequestParam("files")MultipartFile[] files){
        String message = "";
        try {

            List<String> filesNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                try {
                    fileService.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                filesNames.add(file.getOriginalFilename());

            });
            message = "Se subieron los archivos correctamente " +filesNames;
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));


        }catch (Exception e){
            message = "Fallo al subir los archivos";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));

        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<FileModel>> getFiles(){
        List<FileModel> fileInfos = fileService.loadAll().map(path -> {
            String filename =path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                    path.getFileName().toString()).build().toString();
            return new FileModel(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);

    }
    @GetMapping("files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){

        try {
            Resource file = fileService.load(filename);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\""+ file.getFilename() + "\"").body(file);
        } catch (IOException e) {
            throw new RuntimeException("Error");

        }
    }
    @GetMapping("/delete/{filename:.+}")
    public ResponseEntity<FileMessage> deleteFile(@PathVariable String filename){

        String message = "";
        try {
            message = fileService.deletefile(filename);
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));

        }
    }

}