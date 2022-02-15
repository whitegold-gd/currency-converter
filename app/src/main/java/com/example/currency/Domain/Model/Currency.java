package com.example.currency.Domain.Model;

import org.jetbrains.annotations.NotNull;

public class Currency {
    @NotNull
    private String id;
    private String name;
    private float conversion_rate;

    public Currency(String name, float conversion_rate) {
        this.name = name;
        this.conversion_rate = conversion_rate;
    }

    public Currency() {
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

    public float getConversion_rate() {
        return conversion_rate;
    }

    public void setConversion_rate(float conversion_rate) {
        this.conversion_rate = conversion_rate;
    }
}
