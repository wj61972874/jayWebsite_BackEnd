package com.jayblog.weisite.repository;

import com.jayblog.weisite.domain.Tag;
import com.jayblog.weisite.dto.ArticleDTO;
import com.jayblog.weisite.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT a.id, a.title, a.description, a.markdown_src, a.created_at, a.updated_at, " +
            "GROUP_CONCAT(t.name) AS tags " +
            "FROM article a " +
            "LEFT JOIN article_tag at ON a.id = at.article_id " +
            "LEFT JOIN tag t ON at.tag_id = t.id " +
            "GROUP BY a.id, a.title, a.description, a.markdown_src, a.created_at, a.updated_at",
            nativeQuery = true)
    List<Object[]> findAllArticlesWithTags();

    @Query("SELECT a FROM Article a WHERE (:title IS NULL OR a.title LIKE %:title%)")
    Page<Article> findByTitle(@Param("title") String title, Pageable pageable);

    @Query("SELECT t FROM Tag t JOIN t.articles a WHERE a.id IN :articleIds")
    List<Tag> findTagsByArticleIds(@Param("articleIds") List<Long> articleIds);

    Page<Article> findByTitleContaining(String title, Pageable pageable);

    @Query("SELECT DISTINCT a FROM Article a LEFT JOIN FETCH a.tags t WHERE (:title IS NULL OR a.title LIKE %:title%)")
    List<Article> findByFilters(@Param("title") String title);
}