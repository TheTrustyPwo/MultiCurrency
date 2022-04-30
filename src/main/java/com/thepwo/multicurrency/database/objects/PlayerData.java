package com.thepwo.multicurrency.database.objects;

import com.google.common.collect.ImmutableMap;
import com.thepwo.multicurrency.MultiCurrency;

import java.util.Map;
import java.util.UUID;

public record PlayerData(UUID uuid, Map<String, Double> currencyData) {
    public double getCurrency(String currency) {
        return this.currencyData.getOrDefault(currency, 0D);
    }

    public void setCurrency(String currency, double amount) {
        this.currencyData.put(currency.toLowerCase(), amount);
    }

    public void addCurrency(String currency, double amount) {
        setCurrency(currency, getCurrency(currency) + amount);
    }
}
