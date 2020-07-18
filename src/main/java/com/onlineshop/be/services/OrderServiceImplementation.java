package com.onlineshop.be.services;

import com.onlineshop.be.enums.OrderAndProductStatus;
import com.onlineshop.be.enums.Results;
import com.onlineshop.be.exceptions.CustomizedExceptions;
import com.onlineshop.be.model.OrderMain;
import com.onlineshop.be.model.ProductInOrder;
import com.onlineshop.be.model.ProductInfo;
import com.onlineshop.be.repository.OrderRepo;
import com.onlineshop.be.repository.ProductInOrderRepo;
import com.onlineshop.be.repository.ProductInfoRepo;
import com.onlineshop.be.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    OrderRepo orderRepository;
    @Autowired
    UserRepo userRepository;
    @Autowired
    ProductInfoRepo productInfoRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInOrderRepo productInOrderRepository;

    @Override
    public Page<OrderMain> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<OrderMain> findByStatus(Integer status, Pageable pageable) {
        return orderRepository.findAllByOrderStatusOrderByCreateTimeDesc(status, pageable);
    }

    @Override
    public Page<OrderMain> findByBuyerEmail(String email, Pageable pageable) {
        return orderRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email, pageable);
    }

    @Override
    public Page<OrderMain> findByBuyerPhone(String phone, Pageable pageable) {
        return orderRepository.findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(phone, pageable);
    }

    @Override
    public OrderMain findOne(Long orderId) {
        OrderMain orderMain = orderRepository.findByOrderId(orderId);
        if(orderMain == null) {
            throw new CustomizedExceptions(Results.ORDER_NOT_FOUND);
        }
        return orderMain;
    }

    @Override
    public OrderMain finish(Long orderId) {
        OrderMain orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderAndProductStatus.NEW.getCode())) {
            throw new CustomizedExceptions(Results.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderAndProductStatus.FINISHED.getCode());
        orderRepository.save(orderMain);
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public OrderMain cancel(Long orderId) {
        OrderMain orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderAndProductStatus.NEW.getCode())) {
            throw new CustomizedExceptions(Results.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderAndProductStatus.CANCELED.getCode());
        orderRepository.save(orderMain);


        Iterable<ProductInOrder> products = orderMain.getProducts();
        for(ProductInOrder productInOrder : products) {
            ProductInfo productInfo = productInfoRepository.findProductById(productInOrder.getProductId());
            if(productInfo != null) {
                productService.increaseStock(productInOrder.getProductId(), productInOrder.getCount());
            }
        }
        return orderRepository.findByOrderId(orderId);
    }
}
