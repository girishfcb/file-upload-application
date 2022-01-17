package com.girish.projects.service;

import com.girish.projects.domain.FileContent;
import com.girish.projects.repository.FileContentRepository;
import com.girish.projects.service.dto.FileContentDTO;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileUploadService {

    private final Logger log = LoggerFactory.getLogger(FileUploadService.class);
    private static final Integer USER_NAME_POSITION = 0;
    private static final Integer CONTENT_POSITION = 1;

    private static final String DELIMITER = ",";
    private static final String NEW_LINE = "\n";

    @Autowired
    FileContentRepository fileContentRepository;

    public void uploadContentsToDatabase(String contentLine) {
        String[] contents = contentLine.split(";");
        String username = contents[USER_NAME_POSITION];
        String content = contents[CONTENT_POSITION];
        FileContent fileContent = new FileContent();
        fileContent.setUsername(username);
        fileContent.setContent(content);
        fileContent.setUploadDate(Instant.now());
        fileContentRepository.save(fileContent);
    }

    public List<FileContentDTO> getAllFileContents() {
        return fileContentRepository.findAll().stream().map(FileContentDTO::new).collect(Collectors.toList());
    }

    public void writeContentToOutputStream(List<FileContentDTO> allFileContents, ServletOutputStream outputStream) throws IOException {
        StringBuffer fileContent = new StringBuffer();
        String csvHeader = "Username, Content, Uploaded Date";
        fileContent.append(csvHeader);
        fileContent.append(DELIMITER);
        fileContent.append(NEW_LINE);

        for (FileContentDTO fileContentDTO : allFileContents) {
            fileContent.append(fileContentDTO.getUsername());
            fileContent.append(DELIMITER);
            fileContent.append(fileContentDTO.getContent());
            fileContent.append(DELIMITER);
            fileContent.append(fileContentDTO.getUploadDate());
            fileContent.append(NEW_LINE);
        }

        outputStream.write(fileContent.toString().getBytes());
        outputStream.flush();
    }
}
