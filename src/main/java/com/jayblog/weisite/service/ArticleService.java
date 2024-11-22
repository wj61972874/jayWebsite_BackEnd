package com.jayblog.weisite.service;

import com.jayblog.weisite.dto.ArticleDTO;
import com.jayblog.weisite.dto.ArticlesQueryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    String getArticleMarkdownById(Long id);
    List<ArticleDTO> getAllArticlesWithTags();

    Page<ArticleDTO> getArticlesByFilter(ArticlesQueryDto articlesQueryDto);

}

