package tacos.data;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


import tacos.Order;
import tacos.User;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}
