package com.girish.projects.web.rest;

import com.girish.projects.repository.UserRepository;
import com.girish.projects.service.FileUploadService;
import com.girish.projects.service.dto.FileContentDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileUploadResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private static class FileUploadResourceException extends RuntimeException {

        private FileUploadResourceException(String message) {
            super(message);
        }
    }

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/uploadFile")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@RequestParam("memberDataFile") MultipartFile memberFile, HttpServletRequest request)
        throws FileUploadResourceException, IOException {
        InputStream inputStream = memberFile.getInputStream();
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
            .lines()
            .forEach(line -> {
                fileUploadService.uploadContentsToDatabase(line);
            });
    }

    @GetMapping("/getAllContent")
    public List<FileContentDTO> getBlogContent() throws FileUploadResourceException {
        return fileUploadService.getAllFileContents();
    }

    @GetMapping("/downloadContentFile")
    public void downloadContentFile(HttpSession httpSession, HttpServletResponse response) throws IOException {
        response.setHeader(HttpHeaders.CONTENT_TYPE, "text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"all-blog-contents.csv\"");
        fileUploadService.writeContentToOutputStream(fileUploadService.getAllFileContents(), response.getOutputStream());
    }
}
