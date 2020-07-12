package com.onlineshop.be.services;

import com.onlineshop.be.model.ProductInOrder;
import com.onlineshop.be.model.User;
import org.springframework.stereotype.Service;

@Service
public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductInOrder findOne(String itemId, User user);
}