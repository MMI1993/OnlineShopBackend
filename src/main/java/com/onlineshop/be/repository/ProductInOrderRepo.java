package com.onlineshop.be.repository;

import com.onlineshop.be.model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInOrderRepo extends JpaRepository<ProductInOrder, Long> {

}
