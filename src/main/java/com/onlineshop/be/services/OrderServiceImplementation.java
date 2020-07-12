package com.onlineshop.be.services;

import com.onlineshop.be.model.OrderMain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplementation implements OrderService{
    @Override
    public Page<OrderMain> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<OrderMain> findByStatus(Integer status, Pageable pageable) {
        return null;
    }

    @Override
    public Page<OrderMain> findByBuyerEmail(String email, Pageable pageable) {
        return null;
    }

    @Override
    public Page<OrderMain> findByBuyerPhone(String phone, Pageable pageable) {
        return null;
    }

    @Override
    public OrderMain findOne(Long orderId) {
        return null;
    }

    @Override
    public OrderMain finish(Long orderId) {
        return null;
    }

    @Override
    public OrderMain cancel(Long orderId) {
        return null;
    }
}
