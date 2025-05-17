package com.customer_management_system.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author maleeshasa
 * @Date 2025-05-18
 */
@Setter
@Getter
@Builder
public class PageResponse {
    private int totalPages;
    private int currentPage;
    private long totalElements;
    private Object dataList;
}
