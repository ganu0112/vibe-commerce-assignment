package com.vibecommerce.model;

import lombok.*;
import java.time.Instant;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Receipt {
    private List<CartItem> items;
    private double total;
    private Instant timestamp;
    private String buyerName;
    private String buyerEmail;
}
