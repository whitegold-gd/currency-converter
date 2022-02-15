package com.example.currency.Domain.Model;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class DataFromServerTemp {
    @NotNull
    String base_code;
    HashMap<String, Float> conversion_rates;

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public HashMap<String, Float> getConversion_rates() {
        return conversion_rates;
    }

    public void setConversion_rates(HashMap<String, Float> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}
