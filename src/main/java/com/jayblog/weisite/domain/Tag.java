package com.jayblog.weisite.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles;
}