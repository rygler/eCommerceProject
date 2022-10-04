package com.example.ecommerceproject.model.persistence.repositories;

import com.example.ecommerceproject.model.persistence.Cart;
import com.example.ecommerceproject.model.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
