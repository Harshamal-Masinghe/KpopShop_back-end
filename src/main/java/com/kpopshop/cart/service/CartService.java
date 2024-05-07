package com.kpopshop.cart.service;

import com.kpopshop.cart.model.Cart;
import com.kpopshop.cart.model.CartItem;
import com.kpopshop.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addItemToCart(String productId) {
        Cart cart = getCart(); // Retrieve the user's cart
        boolean itemExists = false;

        // Check if the item already exists in the cart
        for (CartItem item : cart.getItems()) {
            if (item.getProductId().equals(productId)) {
                itemExists = true;
                break;
            }
        }

        // If the item doesn't exist, add it to the cart
        if (!itemExists) {
            CartItem newItem = new CartItem();
            newItem.setProductId(productId);

            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    public Cart updateCartItem(String itemId) {
        Cart cart = getCart(); // Retrieve the user's cart

        // Find the item by ID and update its quantity
        for (CartItem item : cart.getItems()) {
            if (item.getId().equals(itemId)) {

                break;
            }
        }

        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(String itemId) {
        Cart cart = getCart(); // Retrieve the user's cart

        // Remove the item from the cart
        cart.getItems().removeIf(item -> item.getId().equals(itemId));

        return cartRepository.save(cart);
    }

    public Cart getCart() {
        // For simplicity, let's assume the cart is identified by a user ID
        // Replace this logic with your actual method to retrieve the user's cart
        return new Cart(); // Placeholder, replace with actual logic
    }

}
