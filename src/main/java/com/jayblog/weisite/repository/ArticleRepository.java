package com.jayblog.weisite.repository;

import com.jayblog.weisite.dto.ArticleDTO;
import com.jayblog.weisite.domain.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT a.id, a.title, a.description, a.markdownSrc, a.created_at, a.updated_at, " +
            "GROUP_CONCAT(t.name) AS tags " +
            "FROM article a " +
            "LEFT JOIN article_tag at ON a.id = at.article_id " +
            "LEFT JOIN tag t ON at.tag_id = t.id " +
            "GROUP BY a.id, a.title, a.description, a.markdownSrc, a.created_at, a.updated_at",
            nativeQuery = true)
    List<Object[]> findAllArticlesWithTags();
}