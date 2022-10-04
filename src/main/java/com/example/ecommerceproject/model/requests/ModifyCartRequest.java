package com.example.ecommerceproject.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModifyCartRequest {

    @JsonProperty
    private String username;

    @JsonProperty
    private long itemId;

    @JsonProperty
    private int quantity;

    public String getUsername() {
        return username;
    }

    public ModifyCartRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public long getItemId() {
        return itemId;
    }

    public ModifyCartRequest setItemId(long itemId) {
        this.itemId = itemId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ModifyCartRequest setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}