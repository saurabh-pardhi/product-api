package com.zest.productapi.controller;

import com.zest.productapi.dto.*;
import com.zest.productapi.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE PRODUCT
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.ok(response);
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) {

        ProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    // GET ALL PRODUCTS WITH PAGINATION
    @GetMapping
    public ResponseEntity<ProductPageResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        ProductPageResponse response = productService.getAllProducts(page, size);
        return ResponseEntity.ok(response);
    }

    // UPDATE PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Integer id,
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    // DELETE PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    // POST ITEMS
    @PostMapping("/{id}/items")
    public ResponseEntity<ItemResponse> addItem(
            @PathVariable("id") Long productId,
            @Valid @RequestBody ItemRequest request) {

        ItemResponse response = productService.addItemToProduct(productId, request);
        return ResponseEntity.ok(response);
    }
}
