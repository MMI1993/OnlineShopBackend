package services;

import enums.OrderAndProductStatus;
import enums.Results;
import exceptions.CustomizedExceptions;
import model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.ProductInfoRepo;

import javax.transaction.Transactional;

public class ProductServiceImplementation implements ProductService {

    @Autowired
    ProductInfoRepo productInfoRepository;

    @Autowired
    CategoryService categoryService;

    @Override
    public ProductInfo findOne(String productId) {

        ProductInfo productInfo = productInfoRepository.findByProductId(productId);
        return productInfo;
    }

    @Override
    public Page<ProductInfo> findUpAll(Pageable pageable) {
        return productInfoRepository.findAllByProductStatusOrderByProductIdAsc(OrderAndProductStatus.UP.getCode(),pageable);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAllByOrderByProductId(pageable);
    }

    @Override
    public Page<ProductInfo> findAllInCategory(Integer categoryType, Pageable pageable) {
        return productInfoRepository.findAllByCategoryTypeOrderByProductIdAsc(categoryType, pageable);
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