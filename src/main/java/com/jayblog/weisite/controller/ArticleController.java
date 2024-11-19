package com.jayblog.weisite.controller;

import com.jayblog.weisite.dto.ArticleDTO;
import com.jayblog.weisite.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jayBlog")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public List<ArticleDTO> getAllArticlesWithTags() {
        return articleService.getAllArticlesWithTags();
    }
}