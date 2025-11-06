package com.vibecommerce.controller;

import com.vibecommerce.model.Product;
import com.vibecommerce.repo.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductRepository repo;
    public ProductController(ProductRepository repo){ this.repo = repo; }

    @GetMapping
    public List<Product> all(){ return repo.findAll(); }
}
