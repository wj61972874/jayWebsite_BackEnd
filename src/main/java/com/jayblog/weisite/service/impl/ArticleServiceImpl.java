package com.jayblog.weisite.service.impl;
import com.jayblog.weisite.domain.Article;
import com.jayblog.weisite.domain.Tag;
import com.jayblog.weisite.dto.ArticleDTO;
import com.jayblog.weisite.dto.ArticleEditDto;
import com.jayblog.weisite.dto.ArticlesQueryDto;
import com.jayblog.weisite.dto.TagDto;
import com.jayblog.weisite.repository.ArticleRepository;
import com.jayblog.weisite.repository.TagRepository;
import com.jayblog.weisite.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ArticleServiceImpl implements ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public String getArticleMarkdownById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        System.out.println("article: " + article);
        if (article.isPresent()) {
            return article.get().getMarkdownSrc();
        } else {
            return null;
        }
    }

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
            if (result[6] != null) {
                article.setTags(Arrays.asList(((String) result[6]).split(",")));
            } else {
                article.setTags(new ArrayList<>());
            }
            articles.add(article);
        }

        return articles;
    }


@Override
public Page<ArticleDTO> getArticlesByFilter(ArticlesQueryDto articlesQueryDto) {
    Pageable pageable = PageRequest.of(articlesQueryDto.getPageNum() - 1, articlesQueryDto.getPageSize());

    List<Article> articles = articleRepository.findByFilters(articlesQueryDto.getTitle());

    logger.debug("Fetched articles: {}", articles);
    List<ArticleDTO> articleDTOs = articles.stream().map(this::convertToDto).collect(Collectors.toList());

    return new PageImpl<>(articleDTOs, pageable, articleDTOs.size());
}

    @Override
    public List<TagDto> getTags() {
        List<Tag> tags = tagRepository.findAll();
        logger.debug("Fetched tags: {}", tags);
        return tags.stream().map(tag -> new TagDto(tag.getId(), tag.getName())).collect(Collectors.toList());
    }

    @Override
    public void saveOrUpdateArticle(ArticleEditDto articleEditDto) {
        Article article;
        if(articleEditDto.getId() != null) {
            article = articleRepository.findById(articleEditDto.getId()).orElse(new Article());
        }else {
            article = new Article();
            article.setCreatedAt(LocalDateTime.now());
        }

        article.setTitle(articleEditDto.getTitle());
        article.setDescription(articleEditDto.getDescription());
        article.setMarkdownSrc(articleEditDto.getMarkdownSrc());
        article.setUpdatedAt(LocalDateTime.now());

        Set<Tag> tags = new HashSet<>();
        for(String tagName: articleEditDto.getTags()) {
            Tag tag = tagRepository.findByName(tagName).orElseGet(() -> {
                Tag newTag = new Tag();
                newTag.setName(tagName);
                return tagRepository.save(newTag);
            });
            tags.add(tag);
        }
        article.setTags(tags);
        articleRepository.save(article);
    }

    private ArticleDTO convertToDto(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setDescription(article.getDescription());
        articleDTO.setMarkdownSrc(article.getMarkdownSrc());
        articleDTO.setCreatedAt(article.getCreatedAt());
        articleDTO.setUpdatedAt(article.getUpdatedAt());
        articleDTO.setTags(article.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()));
        return articleDTO;
    }

}