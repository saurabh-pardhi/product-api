package com.zest.productapi.service.impl;

import com.zest.productapi.dto.*;
import com.zest.productapi.entity.Item;
import com.zest.productapi.entity.Product;
import com.zest.productapi.exception.ResourceNotFoundException;
import com.zest.productapi.repository.ItemRepository;
import com.zest.productapi.repository.ProductRepository;
import com.zest.productapi.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;



    public ProductServiceImpl(ProductRepository productRepository,ItemRepository itemRepository) {
        this.productRepository = productRepository;
        this.itemRepository=itemRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setCreatedBy(request.getCreatedBy());

        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(Integer id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setProductName(request.getProductName());
        product.setModifiedBy(request.getCreatedBy());

        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }

    @Override
    public ProductResponse getProductById(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return mapToResponse(product);
    }

    @Override
    public ProductPageResponse getAllProducts(int page, int size) {

        // Pageable with descending sort by id
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        // Fetch paginated products
        Page<Product> productPage = productRepository.findAll(pageable);

        // Map entities to DTO
        List<ProductResponse> products = productPage.getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();

        // Return clean paginated response
        return new ProductPageResponse(
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                products
        );
    }

    @Override
    public ItemResponse addItemToProduct(Long productId, ItemRequest request) {
        Product product = productRepository.findById(Math.toIntExact(productId))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Item item = new Item();
        item.setProduct(product);
        item.setQuantity(request.getQuantity());

        // Save item
        Item savedItem = itemRepository.save(item);

        // Return DTO
        ItemResponse response = new ItemResponse();
        response.setId(savedItem.getId());
        response.setQuantity(savedItem.getQuantity());

        return response;
    }

    @Override
    public void deleteProduct(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productRepository.delete(product);
    }

    // 🔁 ENTITY → DTO
    private ProductResponse mapToResponse(Product product) {

        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setProductName(product.getProductName());
        response.setCreatedBy(product.getCreatedBy());
        response.setCreatedOn(product.getCreatedOn());
        response.setModifiedBy(product.getModifiedBy());
        response.setModifiedOn(product.getModifiedOn());

        if (product.getItems() != null) {

            List<ItemResponse> itemResponses = product.getItems()
                    .stream()
                    .map(item -> {
                        ItemResponse itemResponse = new ItemResponse();
                        itemResponse.setId(item.getId());
                        itemResponse.setQuantity(item.getQuantity());
                        return itemResponse;
                    })
                    .collect(Collectors.toList());

            response.setItems(itemResponses);
        } else {
            response.setItems(null);
        }

        return response;
    }
}
