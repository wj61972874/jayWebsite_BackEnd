package com.jayblog.weisite.controller;

import com.jayblog.weisite.common.PageResponse;
import com.jayblog.weisite.common.ResponseBean;
import com.jayblog.weisite.domain.Tag;
import com.jayblog.weisite.dto.ArticleDTO;
import com.jayblog.weisite.dto.ArticleEditDto;
import com.jayblog.weisite.dto.ArticlesQueryDto;
import com.jayblog.weisite.dto.TagDto;
import com.jayblog.weisite.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manage/article")
public class ArticleManageController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/list/filter")
    public ResponseBean<PageResponse<ArticleDTO>> getArticleList(@RequestBody ArticlesQueryDto articleQueryDTO) {
        Page<ArticleDTO> articlePage = articleService.getArticlesByFilter(articleQueryDTO);
        System.out.println("articlePage=====: " + articlePage);
        PageResponse<ArticleDTO> pageResponse = new PageResponse<>(
                articlePage.getContent(),
                articleQueryDTO.getPageNum(),
                articleQueryDTO.getPageSize(),
                articlePage.getTotalElements(),
                (int) articlePage.getTotalPages()
        );
        return ResponseBean.success(pageResponse);
    }

    @GetMapping("/tags/list")
    public ResponseBean<List<TagDto>> getTags() {
        return ResponseBean.success(articleService.getTags());
    }

    @PostMapping("/edit")
    public ResponseBean<Void> editArticle(@RequestBody ArticleEditDto articleEditRequest) {
        articleService.saveOrUpdateArticle(articleEditRequest);
        return ResponseBean.success(null);
    }
}