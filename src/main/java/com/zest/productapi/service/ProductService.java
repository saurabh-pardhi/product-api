package com.zest.productapi.service;

import com.zest.productapi.dto.*;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(Integer id, ProductRequest request);

    ProductResponse getProductById(Integer id);

    ProductPageResponse getAllProducts(int page, int size);

    ItemResponse addItemToProduct(Long productId, ItemRequest request);
    void deleteProduct(Integer id);
}
