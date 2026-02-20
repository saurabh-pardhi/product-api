package com.zest.productapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Created by is required")
    private String createdBy;

    public ProductRequest() {
    }

    public ProductRequest(String productName, String createdBy) {
        this.productName = productName;
        this.createdBy = createdBy;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
