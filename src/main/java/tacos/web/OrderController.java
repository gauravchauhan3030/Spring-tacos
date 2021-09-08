package tacos.web;
import java.awt.print.Pageable;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
  
  private OrderRepository orderRepo;
  
  private OrderProps orderProps;

  public OrderController(OrderRepository orderRepo, OrderProps orderProps) {
    this.orderRepo = orderRepo;
    this.orderProps = orderProps;
  }
  
  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, 
		  @AuthenticationPrincipal User user) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    
    order.setUser(user);
    
    orderRepo.save(order);
    sessionStatus.setComplete();
    
    return "redirect:/";
  }
  
  @GetMapping
  public String ordersForUsers(@AuthenticationPrincipal User user, Model model) {
	  Pageable pageable = (Pageable) PageRequest.of(0, orderProps.getPageSize());
	  model.addAttribute("orders",
			  orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
	  return "orderList";
  }

}
