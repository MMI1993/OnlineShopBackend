package services;

import model.Cart;
import model.ProductInOrder;
import model.ProductInfo;
import model.User;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

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
