package com.jayblog.weisite.controller;

import com.jayblog.weisite.common.ResponseBean;
import com.jayblog.weisite.dto.ArticleDTO;
import com.jayblog.weisite.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jayBlog")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public ResponseBean<List<ArticleDTO>> getAllArticlesWithTags() {
        return ResponseBean.success(articleService.getAllArticlesWithTags());
    }

    @GetMapping("/article/{id}")
    public ResponseBean<String> getArticleById(@PathVariable Long id) {
        String markdownSrc = articleService.getArticleMarkdownById(id);
        if (markdownSrc != null) {
            return ResponseBean.success(markdownSrc);
        } else {
            return ResponseBean.error("Article not found");
        }
    }
}