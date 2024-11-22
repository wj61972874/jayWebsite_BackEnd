package com.jayblog.weisite.common;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> data;
    private int pageNumber;
    private int pageSize;
    private long totalCount;
    private int totalPages;

    public PageResponse(List<T> content, int pageNumber, int pageSize, long totalElements, int totalPages) {
        this.data = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalCount = totalElements;
        this.totalPages = totalPages;
    }

}
