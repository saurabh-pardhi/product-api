//package com.zest.productapi.repository;
//
//import com.zest.productapi.entity.Product;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class ProductRepositoryTest {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Test
//    void testSaveProduct() {
//        Product product = new Product("Laptop", "Saurabh", LocalDateTime.now());
//        Product saved = productRepository.save(product);
//
//        assertNotNull(saved.getId());
//        assertEquals("Laptop", saved.getProductName());
//    }
//
//    @Test
//    void testFindById() {
//        Product product = new Product("Phone", "Saurabh", LocalDateTime.now());
//        productRepository.save(product);
//
//        Optional<Product> found = productRepository.findById(product.getId());
//        assertTrue(found.isPresent());
//        assertEquals("Phone", found.get().getProductName());
//    }
//
//    @Test
//    void testFindByProductName() {
//        Product product = new Product("Tablet", "Saurabh", LocalDateTime.now());
//        productRepository.save(product);
//
//        Optional<Product> found = productRepository.findByProductName("Tablet");
//        assertTrue(found.isPresent());
//        assertEquals("Tablet", found.get().getProductName());
//    }
//
//    @Test
//    void testDeleteProduct() {
//        Product product = new Product("Monitor", "Saurabh", LocalDateTime.now());
//        productRepository.save(product);
//
//        productRepository.delete(product);
//        Optional<Product> deleted = productRepository.findById(product.getId());
//        assertFalse(deleted.isPresent());
//    }
//
//    @Test
//    void testFindAllProducts() {
//        Product product1 = new Product("Keyboard", "Saurabh", LocalDateTime.now());
//        Product product2 = new Product("Mouse", "Saurabh", LocalDateTime.now());
//        productRepository.save(product1);
//        productRepository.save(product2);
//
//        List<Product> products = productRepository.findAll();
//        assertEquals(2, products.size());
//    }
//}