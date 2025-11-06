package com.vibecommerce;

import com.vibecommerce.model.Product;
import com.vibecommerce.repo.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private final ProductRepository repo;
    public DataLoader(ProductRepository repo){ this.repo = repo; }

    @PostConstruct
    public void load() {
        if(repo.count() == 0) {
            repo.save(new Product(null,"Wireless Headphones","Bluetooth over-ear headphones",59.99));
            repo.save(new Product(null,"Smart Watch","Fitness tracking smart watch",129.99));
            repo.save(new Product(null,"Coffee Mug","Ceramic mug, 350ml",9.99));
            repo.save(new Product(null,"Notebook","A5 ruled notebook",5.99));
            repo.save(new Product(null,"Desk Lamp","LED desk lamp with dimmer",24.99));
            repo.save(new Product(null,"USB-C Cable","1.5m USB-C charging cable",7.49));
        }
    }
}
