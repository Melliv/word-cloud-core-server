package com.example.wordcloudcoreserver.dto;

import com.example.wordcloudcoreserver.enums.WordCloudStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TextFileDTO {

    private Long id;
    private String name;
    private Long size;
    private WordCloudStatus status;
}
