package com.girish.projects.repository;

import com.girish.projects.domain.FileContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileContentRepository extends JpaRepository<FileContent, Long> {}
