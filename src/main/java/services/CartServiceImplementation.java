package services;

import lombok.var;
import model.Cart;
import model.OrderMain;
import model.ProductInOrder;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CartRepo;
import repository.OrderRepo;
import repository.ProductInOrderRepo;
import repository.UserRepo;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CartServiceImplementation implements  CartService {
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepo orderRepository;
    @Autowired
    UserRepo userRepository;

    @Autowired
    ProductInOrderRepo productInOrderRepository;
    @Autowired
    CartRepo cartRepository;
    @Autowired
    UserService userService;


    @Override
    public void addProduct(ProductInOrder product) {

    }

    @Override
    public void removeProduct(ProductInOrder product) {

    }

    @Override
    public void clearProducts() {

    }

    @Override
    public Map<ProductInOrder, Integer> productsInCart() {
        return null;
    }

    @Override
    public BigDecimal totalPrice() {
        return null;
    }

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    @Transactional
    public void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user) {
        Cart finalCart = user.getCart();
        productInOrders.forEach(productInOrder -> {
            Set<ProductInOrder> set = finalCart.getProducts();
            Optional<ProductInOrder> old = set.stream().filter(e -> e.getProductId().equals(productInOrder.getProductId())).findFirst();
            ProductInOrder prod;
            if (old.isPresent()) {
                prod = old.get();
                prod.setCount(productInOrder.getCount() + prod.getCount());
            } else {
                prod = productInOrder;
                prod.setCart(finalCart);
                finalCart.getProducts().add(prod);
            }
            productInOrderRepository.save(prod);
        });
        cartRepository.save(finalCart);

    }


    @Override
    @Transactional
    public void delete(String itemId, User user) {
        var op = user.getCart().getProducts().stream().filter(e -> itemId.equals(e.getProductId())).findFirst();
        op.ifPresent(productInOrder -> {
            productInOrder.setCart(null);
            productInOrderRepository.deleteById(productInOrder.getId());
        });
    }

    @Override
    @Transactional
    public void checkout(User user) {
        // Creat an order
        OrderMain order = new OrderMain(user);
        orderRepository.save(order);

        // clear cart's foreign key & set order's foreign key& decrease stock
        user.getCart().getProducts().forEach(productInOrder -> {
            productInOrder.setCart(null);
            productInOrder.setOrderMain(order);
            productService.decreaseStock(productInOrder.getProductId(), productInOrder.getCount());
            productInOrderRepository.save(productInOrder);
        });
    }
}
