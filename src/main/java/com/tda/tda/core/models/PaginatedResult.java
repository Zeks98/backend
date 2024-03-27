package com.tda.tda.core.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginatedResult<T>{

    private int currentPage;

    private int totalPages;

    private int pageSize;

    private int totalItems;

    private T data;
}