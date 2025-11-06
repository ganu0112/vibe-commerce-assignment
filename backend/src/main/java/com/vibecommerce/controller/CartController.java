package com.vibecommerce.controller;

import com.vibecommerce.model.CartItem;
import com.vibecommerce.model.Receipt;
import com.vibecommerce.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {
    private final ShopService service;
    public CartController(ShopService service){ this.service = service; }

    @GetMapping
    public List<CartItem> getCart(){ return service.getCart(); }

    @PostMapping
    public CartItem addToCart(@RequestBody Map<String,Object> body){
        Long productId = Long.valueOf(String.valueOf(body.get("productId")));
        int qty = Integer.parseInt(String.valueOf(body.getOrDefault("qty",1)));
        return service.addToCart(productId, qty);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id){ service.removeCartItem(id); }

    @PostMapping("/checkout")
    public Receipt checkout(@RequestBody Map<String,String> body){
        String name = body.get("name");
        String email = body.get("email");
        return service.checkout(name,email);
    }
}
