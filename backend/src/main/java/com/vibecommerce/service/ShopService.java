package com.vibecommerce.service;

import com.vibecommerce.model.CartItem;
import com.vibecommerce.model.Product;
import com.vibecommerce.model.Receipt;
import com.vibecommerce.repo.CartItemRepository;
import com.vibecommerce.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ShopService {
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public ShopService(ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public List<Product> listProducts() { return productRepository.findAll(); }

    public CartItem addToCart(Long productId, int qty) {
        Product p = productRepository.findById(productId).orElseThrow();
        CartItem ci = new CartItem();
        ci.setProduct(p);
        ci.setQty(qty);
        return cartItemRepository.save(ci);
    }

    public List<CartItem> getCart() { return cartItemRepository.findAll(); }

    public void removeCartItem(Long id) { cartItemRepository.deleteById(id); }

    public Receipt checkout(String name, String email) {
        List<CartItem> items = cartItemRepository.findAll();
        double total = items.stream().mapToDouble(it -> it.getProduct().getPrice() * it.getQty()).sum();
        Receipt r = new Receipt(items,total, Instant.now(), name, email);
        cartItemRepository.deleteAll();
        return r;
    }
}
