package com.jayblog.weisite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String description;
    private String markdownSrc;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> tags;
}