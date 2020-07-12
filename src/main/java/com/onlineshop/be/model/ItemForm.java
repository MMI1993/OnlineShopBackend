package com.onlineshop.be.model;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ItemForm {
    @NotNull
    private Integer quantity;
    @NotNull
    private String productId;
    }
