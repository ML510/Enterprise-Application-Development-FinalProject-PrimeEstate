package com.realestate.management.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Page<T>{
    private Integer pageNumber;
    private Integer pageSize;
    private String sortBy;
    private String sortDirection;
    private Integer totalElements;
    private Integer totalPages;
    private Boolean last;
    private T content;
}
