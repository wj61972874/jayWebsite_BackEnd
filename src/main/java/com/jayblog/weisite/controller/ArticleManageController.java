package com.jayblog.weisite.controller;

import com.jayblog.weisite.common.PageResponse;
import com.jayblog.weisite.common.ResponseBean;
import com.jayblog.weisite.dto.ArticleDTO;
import com.jayblog.weisite.dto.ArticlesQueryDto;
import com.jayblog.weisite.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
//        return ResponseBean.success(null);
    }
}