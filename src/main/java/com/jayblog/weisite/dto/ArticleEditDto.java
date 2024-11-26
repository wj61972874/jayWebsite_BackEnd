package com.jayblog.weisite.dto;

import lombok.Data;

@Data
public class ArticleEditDto {
    private Long id;
    private String title;
    private String description;
    private String markdownSrc;
    private String[] tags;
}
