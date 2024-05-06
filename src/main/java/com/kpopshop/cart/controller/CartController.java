package com.kpopshop.cart.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kpopshop.cart.model.Cart;
import com.kpopshop.cart.model.CartItem;
import com.kpopshop.cart.service.CartService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;



    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addItemToCart(@RequestParam String productId) {
        return cartService.addItemToCart(productId);
    }

    @PutMapping("/update")
    public Cart updateCartItem(@RequestParam String itemId) {
        return cartService.updateCartItem(itemId);
    }

    @DeleteMapping("/remove")
    public Cart removeItemFromCart(@RequestParam String itemId) {
        return cartService.removeItemFromCart(itemId);
    }

    @GetMapping("/get")
    public Cart getCart() {
        return cartService.getCart();
    }
}



