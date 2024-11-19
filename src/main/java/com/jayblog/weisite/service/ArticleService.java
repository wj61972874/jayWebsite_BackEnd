package com.jayblog.weisite.service;

import com.jayblog.weisite.dto.ArticleDTO;
import java.util.List;

public interface ArticleService {
    List<ArticleDTO> getAllArticlesWithTags();
}