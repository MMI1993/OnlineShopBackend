package com.onlineshop.be.services;

import com.onlineshop.be.enums.OrderAndProductStatus;
import com.onlineshop.be.enums.Results;
import com.onlineshop.be.exceptions.CustomizedExceptions;
import com.onlineshop.be.model.ProductInfo;
import com.onlineshop.be.repository.ProductInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    ProductInfoRepo productInfoRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findById(productId).get();
    }

    @Override
    public Page<ProductInfo> findUpAll(Pageable pageable) {
        return productInfoRepository.findAllByCategoryTypeOrderByIdAsc(OrderAndProductStatus.UP.getCode(),pageable);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAllByOrderById(pageable);
    }

    @Override
    public Page<ProductInfo> findAllInCategory(Integer categoryType, Pageable pageable) {
        return productInfoRepository.findAllByCategoryTypeOrderByIdAsc(categoryType, pageable);
    }

    @Override
    @Transactional
    public void increaseStock(String productId, int amount) {
        ProductInfo productInfo = findOne(productId);
        if (productInfo == null) throw new CustomizedExceptions(Results.PRODUCT_NOT_EXIST);

        int update = productInfo.getProductStock() + amount;
        productInfo.setProductStock(update);
        productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void decreaseStock(String productId, int amount) {
        ProductInfo productInfo = findOne(productId);
        if (productInfo == null) throw new CustomizedExceptions(Results.PRODUCT_NOT_EXIST);

        int update = productInfo.getProductStock() - amount;
        if(update <= 0) throw new CustomizedExceptions(Results.PRODUCT_LIMITED_STOCK );

        productInfo.setProductStock(update);
        productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = findOne(productId);
        if (productInfo == null) throw new CustomizedExceptions(Results.PRODUCT_NOT_EXIST);

        if (productInfo.getProductStatus() == OrderAndProductStatus.DOWN.getCode()) {
            throw new CustomizedExceptions(Results.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(OrderAndProductStatus.DOWN.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = findOne(productId);
        if (productInfo == null) throw new CustomizedExceptions(Results.PRODUCT_NOT_EXIST);

        if (productInfo.getProductStatus() == OrderAndProductStatus.UP.getCode()) {
            throw new CustomizedExceptions(Results.PRODUCT_STATUS_ERROR);
        }

        productInfo.setProductStatus(OrderAndProductStatus.UP.getCode());
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo update(ProductInfo productInfo) {

        // if null throw exception
        categoryService.findByCategoryType(productInfo.getCategoryType());
        if(productInfo.getProductStatus() > 1) {
            throw new CustomizedExceptions(Results.PRODUCT_STATUS_ERROR);
        }


        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return update(productInfo);
    }

    @Override
    public void delete(String productId) {
        ProductInfo productInfo = findOne(productId);
        if (productInfo == null) throw new CustomizedExceptions(Results.PRODUCT_NOT_EXIST);
        productInfoRepository.delete(productInfo);

    }


}