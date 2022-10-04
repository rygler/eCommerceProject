package com.example.ecommerceproject.model.persistence.repositories;

import com.example.ecommerceproject.model.persistence.User;
import com.example.ecommerceproject.model.persistence.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderRepository  extends JpaRepository<UserOrder, Long> {
    List<UserOrder> findByUser(User user);
}
