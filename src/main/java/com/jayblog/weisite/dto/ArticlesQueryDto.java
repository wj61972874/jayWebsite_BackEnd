package com.jayblog.weisite.dto;

import lombok.Data;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

@Data
public class ArticlesQueryDto extends PageDto{
    private String title;
    private List<String> tags;
}
