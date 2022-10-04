package com.example.ecommerceproject.model.persistence.repositories;

import com.example.ecommerceproject.model.persistence.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByName(String name);
}
