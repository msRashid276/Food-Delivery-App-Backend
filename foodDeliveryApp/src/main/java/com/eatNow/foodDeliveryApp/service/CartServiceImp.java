package com.eatNow.foodDeliveryApp.service;

import com.eatNow.foodDeliveryApp.model.Cart;
import com.eatNow.foodDeliveryApp.model.CartItem;
import com.eatNow.foodDeliveryApp.model.Food;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.repository.CartItemRepo;
import com.eatNow.foodDeliveryApp.repository.CartRepo;
import com.eatNow.foodDeliveryApp.repository.FoodRepository;
import com.eatNow.foodDeliveryApp.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CartServiceImp implements CartService{


    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodService foodService;



    @Override
    public CartItem addItemToCart(AddCartItemRequest request, Users user) throws Exception {

        Food food = foodService.findFoodById(request.getFoodId());
        Cart cart = cartRepo.findByCustomerId(user.getId());

        for(CartItem cartItem: cart.getItem()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity()+request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setFood(food);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setTotalPrice(request.getQuantity()*food.getPrice());

        CartItem savedCartItem = cartItemRepo.save(newCartItem);
        cart.getItem().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {

        Optional<CartItem> cartItem = cartItemRepo.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("CartItem not found with this id");
        }

        CartItem item = cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice()*quantity);

        return cartItemRepo.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, Users user) throws Exception {

        Cart cart = cartRepo.findByCustomerId(user.getId());

        Optional<CartItem> cartItem = cartItemRepo.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("CartItem not found with this id");
        }

        CartItem item = cartItem.get();
        cart.getItem().remove(item);
        return cartRepo.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {

        Long total = 0L;

        for(CartItem cartItem:cart.getItem()){
           total+= cartItem.getFood().getPrice()* cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {

        Optional<Cart> cart = cartRepo.findById(id);
        if(cart.isEmpty()){
            throw new Exception("Cart is not found by this id");
        }
        return cart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return cartRepo.findByCustomerId(userId);
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {

        Cart cart = findCartById(userId);
        cart.getItem().clear();
        return cartRepo.save(cart);
    }
}
