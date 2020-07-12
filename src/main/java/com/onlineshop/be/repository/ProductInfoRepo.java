package com.onlineshop.be.repository;

import com.onlineshop.be.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInfoRepo extends JpaRepository<ProductInfo, String> {

//    Page<ProductInfo> findAllByProductStatusOrOrderByIdAsc(Integer productStatus, Pageable pageable);

    Page<ProductInfo> findAllByCategoryTypeOrderByIdAsc(Integer categoryType, Pageable pageable);

    Page<ProductInfo> findAllByOrderById(Pageable pageable);

}
