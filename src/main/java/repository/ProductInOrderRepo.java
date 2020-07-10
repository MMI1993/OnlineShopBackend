package repository;

import model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepo extends JpaRepository<ProductInOrder, Long> {

        }
