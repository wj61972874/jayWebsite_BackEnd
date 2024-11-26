package com.jayblog.weisite.service;

import com.jayblog.weisite.domain.Tag;
import com.jayblog.weisite.dto.ArticleDTO;
import com.jayblog.weisite.dto.ArticleEditDto;
import com.jayblog.weisite.dto.ArticlesQueryDto;
import com.jayblog.weisite.dto.TagDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    String getArticleMarkdownById(Long id);
    List<ArticleDTO> getAllArticlesWithTags();

    Page<ArticleDTO> getArticlesByFilter(ArticlesQueryDto articlesQueryDto);

    List<TagDto> getTags();

    void saveOrUpdateArticle(ArticleEditDto articleEditDto);
}

