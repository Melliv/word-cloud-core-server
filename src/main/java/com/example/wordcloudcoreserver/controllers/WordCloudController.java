package com.example.wordcloudcoreserver.controllers;

import com.example.wordcloudcoreserver.dto.TextFilesPagination;
import com.example.wordcloudcoreserver.models.TextFile;
import com.example.wordcloudcoreserver.services.TextFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class WordCloudController {

    @Autowired
    private TextFileService textFileService;

    @PostMapping(path = "/word-cloud/upload-text-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        textFileService.uploadTextFile(file);
        return "OK";
    }

    @GetMapping(path = "/text-file/{id}")
    public TextFile getTextFile(@PathVariable("id") Long id) {
        TextFile textFile = textFileService.getTextFile(id);
        if (textFile == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return textFileService.getTextFile(id);
    }

    @GetMapping(path = "/text-files")
    public TextFilesPagination getTextFiles(@RequestParam(value = "limit", required = false, defaultValue = "100") Integer limit, @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) throws Exception {
        return textFileService.getTextFiles(limit, offset);
    }

    @GetMapping(path = "/temp")
    public String testSocket() {
        textFileService.temp();
        return "temp";
    }

}