package com.example.wordcloudcoreserver.repositories;

import com.example.wordcloudcoreserver.dto.TextFileDTO;
import com.example.wordcloudcoreserver.models.TextFile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TextFileRepository extends JpaRepository<TextFile, Long> {

    // Custom query
    @Query("SELECT new com.example.wordcloudcoreserver.dto.TextFileDTO(t.id, t.name, t.size, t.status) FROM TextFile t")
    List<TextFileDTO> getTextFiles(Pageable pageable);

    @Query("SELECT COUNT(*) FROM TextFile")
    Integer getTextFilesCount();

}