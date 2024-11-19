package com.jayblog.weisite.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleDTO {
    private Long id;
    private String title;
    private String description;
    private String markdownSrc;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> tags;
}