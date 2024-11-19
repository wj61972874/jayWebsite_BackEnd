package com.jayblog.weisite.service.impl;

import com.jayblog.weisite.dto.ArticleDTO;
import com.jayblog.weisite.repository.ArticleRepository;
import com.jayblog.weisite.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<ArticleDTO> getAllArticlesWithTags() {
        List<Object[]> results = articleRepository.findAllArticlesWithTags();
        List<ArticleDTO> articles = new ArrayList<>();

        for (Object[] result : results) {
            ArticleDTO article = new ArticleDTO();
            article.setId(((Number) result[0]).longValue());
            article.setTitle((String) result[1]);
            article.setDescription((String) result[2]);
            article.setMarkdownSrc((String) result[3]);
            article.setCreatedAt(((Timestamp) result[4]).toLocalDateTime());
            article.setUpdatedAt(((Timestamp) result[5]).toLocalDateTime());
            article.setTags(Arrays.asList(((String) result[6]).split(",")));
            articles.add(article);
        }

        return articles;
    }
}