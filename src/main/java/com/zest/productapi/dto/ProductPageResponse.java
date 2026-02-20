package com.zest.productapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class ProductPageResponse {
    private long totalElements;
    private int totalPages;
    private List<ProductResponse> products;

    public ProductPageResponse(long totalElements, int totalPages, List<ProductResponse> products) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.products = products;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}