package com.altimetrix.ecommerce.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrix.ecommerce.entity.Cart;
import com.altimetrix.ecommerce.entity.Customer;
import com.altimetrix.ecommerce.entity.ItemDetailsForOrder;
import com.altimetrix.ecommerce.entity.Order;
import com.altimetrix.ecommerce.entity.CartItem;
import com.altimetrix.ecommerce.repository.CartItemRepository;
import com.altimetrix.ecommerce.repository.CustomerRepository;
import com.altimetrix.ecommerce.repository.ItemDetailsForOrderRepository;
import com.altimetrix.ecommerce.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ItemDetailsForOrderRepository itemDetailsForOrderRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	
	public void placeOrder(String emailId,String paymentMethod) throws IOException {
		
		Customer customer=customerRepository.findById(emailId).orElseThrow(
				()-> new RuntimeException("Customer with email ID : "+emailId+" doesn't exist in DB"));
		Cart cart=customer.getCart();
		if(cart ==null || cart.getCartItems().isEmpty()) {
			new RuntimeException("Cart is Empty.Checkout can't be proceeded");
		}
        double totalPrice=cart.getTotalPrice();
        Order order=initializeOrderDetails(totalPrice,paymentMethod,customer);
        orderRepository.save(order);
        List<ItemDetailsForOrder> items= initializeItemDetailsForOrder(cart,order);
        order.setCartItems(items);
        sendNotificationToUser(emailId,order);
	}
	
     public List<ItemDetailsForOrder> initializeItemDetailsForOrder(Cart cart,Order order) {
		
		List<CartItem> cartItems= cart.getCartItems();
		List<ItemDetailsForOrder> items=new ArrayList<>();
		
		for(CartItem cartItem : cartItems) {
			ItemDetailsForOrder itemDetailsForOrder=new ItemDetailsForOrder();
			itemDetailsForOrder.setQuantity(cartItem.getQuantity());
			itemDetailsForOrder.setProduct(cartItem.getProduct());
			itemDetailsForOrder.setOrder(order);
			itemDetailsForOrderRepository.save(itemDetailsForOrder);
			items.add(itemDetailsForOrder);
		}
		cartItemRepository.deleteAll(cartItems);
		cartItems.clear();
		cart.setCartItems(cartItems);
		cart.setTotalPrice(0);
		return items;
	}
     
    public Order initializeOrderDetails(double totalPrice,String paymentMethod,Customer customer) {
    	Order order=new Order();
    	if(paymentMethod.equalsIgnoreCase("Cash"))
    		order.setAdvancePayment(false);
    	else
    		order.setAdvancePayment(true);
    	order.setPaymentMethod(paymentMethod);
    	order.setTotalPrice(totalPrice);
    	order.setExpectedDeliveryDate(LocalDate.now());
    	order.setCustomer(customer);
    	order.setStatus("PENDING");
    	return order;
    }
    
    /*Sending E-mail Notification to User */
    public void sendNotificationToUser(String emailId,Order order) throws IOException {
    	Long orderId=order.getId();
    	String subject="Order with ID : "+orderId+" is placed successfully";
    	String orderDetails=order.toString();
    	executor.execute(
    			()->{ 
    					notificationService.sendEmail(emailId,subject,orderDetails);
    				}
    			);
    }
	
}
