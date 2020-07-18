package com.onlineshop.be.repository;

import com.onlineshop.be.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
    Page<Cart> findAllById(Integer id, Pageable pageable);
}
