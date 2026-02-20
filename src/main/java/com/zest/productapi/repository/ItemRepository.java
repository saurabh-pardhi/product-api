package com.zest.productapi.repository;

import com.zest.productapi.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // Fetch all items for a specific product
    List<Item> findByProductId(Long productId);
}