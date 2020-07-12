package com.onlineshop.be.services;

import com.onlineshop.be.model.ProductInOrder;
import com.onlineshop.be.model.User;
import org.springframework.stereotype.Service;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

    @Override
    public void update(String itemId, Integer quantity, User user) {

    }

    @Override
    public ProductInOrder findOne(String itemId, User user) {
        return null;
    }
}
