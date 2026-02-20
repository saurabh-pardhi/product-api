package com.zest.productapi.service;

import com.zest.productapi.dto.ProductResponse;
import com.zest.productapi.entity.Product;
import com.zest.productapi.repository.ProductRepository;
import com.zest.productapi.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Product p1 = new Product();
        p1.setId(1);
        p1.setProductName("Laptop");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = new PageImpl<>(List.of(p1));

        when(productRepository.findAll(pageable)).thenReturn(page);

        Page<Product> result = (Page<Product>) productService.getAllProducts(0, 10);
        assertEquals(1, result.getTotalElements());
        assertEquals("Laptop", result.getContent().get(0).getProductName());
    }

    @Test
    void testGetProductById() {
        Product p = new Product();
        p.setId(1);
        p.setProductName("Laptop");

        when(productRepository.findById(1)).thenReturn(Optional.of(p));

        ProductResponse result = productService.getProductById(1);
        assertNotNull(result);
        assertEquals("Laptop", result.getProductName());
    }
}