package com.eatNow.foodDeliveryApp.service;

import com.eatNow.foodDeliveryApp.model.*;
import com.eatNow.foodDeliveryApp.repository.AddressRepo;
import com.eatNow.foodDeliveryApp.repository.OrderItemRepo;
import com.eatNow.foodDeliveryApp.repository.OrderRepo;
import com.eatNow.foodDeliveryApp.repository.UserRepo;
import com.eatNow.foodDeliveryApp.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;



    @Override
    public Order createOrder(OrderRequest request, Users user) throws Exception {

        Address shippingAddress = request.getAddress();
        Address savedAddress = addressRepo.save(shippingAddress);

        if (!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepo.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());

        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setRestaurant(restaurant);

        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItemsList = new ArrayList<>();

        for (CartItem cartItem : cart.getItem()){
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderItem = orderItemRepo.save(orderItem);
            orderItemsList.add(savedOrderItem);
        }

        Long totalPrice = cartService.calculateCartTotals(cart);

        createdOrder.setItems(orderItemsList);
        createdOrder.setTotalPrice(totalPrice);


        Order savedOrder = orderRepo.save(createdOrder);
        restaurant.getOrders().add(savedOrder);

        return savedOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {

        Order order = findOrderById(orderId);

        if(orderStatus.equals("OUT_FOR_DELIVERY")
                || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING")
        ){
            order.setOrderStatus(orderStatus);
            return orderRepo.save(order);

        }
        throw new Exception("please select a valid order status");
    }



    @Override
    public void cancelOrder(Long orderId) throws Exception {

        Order order = findOrderById(orderId);
        orderRepo.deleteById(orderId);
    }


    @Override
    public List<Order> getUserOrders(Long userId) throws Exception {
        return orderRepo.findByCustomerId(userId);
    }


    @Override
    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception {

        List<Order> orders = orderRepo.findByRestaurantId(restaurantId);

        if(orderStatus!=null){
            orders = orders.stream().filter(order->order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {

        Optional<Order> order = orderRepo.findById(orderId);
        if(order.isEmpty()){
            throw new Exception("Order not found with this id");
        }

        return order.get();
    }
}
