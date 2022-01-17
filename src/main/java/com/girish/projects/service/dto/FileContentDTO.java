package com.girish.projects.service.dto;

import com.girish.projects.domain.FileContent;
import com.girish.projects.domain.User;
import java.time.Instant;

/**
 * A DTO representing a user, with only the public attributes.
 */
public class FileContentDTO {

    private Long id;

    private String username;

    private String content;

    private Instant uploadDate;

    public FileContentDTO() {}

    public FileContentDTO(FileContent fileContent) {
        this.id = fileContent.getId();
        this.username = fileContent.getUsername();
        this.content = fileContent.getContent();
        this.uploadDate = fileContent.getUploadDate();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Instant uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "FileContentDTO [content=" + content + ", id=" + id + ", uploadDate=" + uploadDate + ", username=" + username + "]";
    }
}
