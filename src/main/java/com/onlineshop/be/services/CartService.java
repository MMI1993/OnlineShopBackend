package com.onlineshop.be.services;

import com.onlineshop.be.model.Cart;
import com.onlineshop.be.model.ProductInOrder;
import com.onlineshop.be.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
@Service
public interface CartService {
    void addProduct(ProductInOrder product);

    void removeProduct(ProductInOrder product);

    void clearProducts();

    Map<ProductInOrder, Integer> productsInCart();

    BigDecimal totalPrice();

    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
