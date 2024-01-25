package com.example.wordcloudcoreserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextFilesPagination {
    private List<TextFileDTO> files;
    private Integer totalCount;
}
