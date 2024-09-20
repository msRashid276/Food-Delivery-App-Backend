package com.eatNow.foodDeliveryApp.controller;


import com.eatNow.foodDeliveryApp.model.Cart;
import com.eatNow.foodDeliveryApp.model.CartItem;
import com.eatNow.foodDeliveryApp.model.Users;
import com.eatNow.foodDeliveryApp.request.AddCartItemRequest;
import com.eatNow.foodDeliveryApp.request.UpdateCartItemRequest;
import com.eatNow.foodDeliveryApp.service.CartService;
import com.eatNow.foodDeliveryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest request, @RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        CartItem cartItem = cartService.addItemToCart(request,user);
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    @PostMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemToCart(@RequestBody UpdateCartItemRequest request, @RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        CartItem cartItem = cartService.updateCartItemQuantity(request.getCartItemId(),request.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    @PutMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItemQuantity(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        Cart cart = cartService.removeItemFromCart(id,user);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String authHeader) throws Exception {
        Users user = userService.findUserByAuthorizationHeader(authHeader);

        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }



}
