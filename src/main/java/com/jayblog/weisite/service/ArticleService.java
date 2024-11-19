package com.jayblog.weisite.service;

import com.jayblog.weisite.dto.ArticleDTO;
import java.util.List;

public interface ArticleService {
    String getArticleMarkdownById(Long id);
    List<ArticleDTO> getAllArticlesWithTags();
}