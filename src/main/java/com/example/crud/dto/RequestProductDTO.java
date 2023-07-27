package com.example.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequestProductDTO {
    private String id;

    @NotBlank
    private String name;

    @NotNull
    private Integer price_in_cents;

    public RequestProductDTO() {
    }

    public RequestProductDTO(String id, @NotBlank String name, @NotNull Integer price_in_cents) {
        this.id = id;
        this.name = name;
        this.price_in_cents = price_in_cents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice_in_cents() {
        return price_in_cents;
    }

    public void setPrice_in_cents(Integer price_in_cents) {
        this.price_in_cents = price_in_cents;
    }

}
