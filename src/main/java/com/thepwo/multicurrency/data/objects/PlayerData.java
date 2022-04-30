package com.thepwo.multicurrency.data.objects;

import com.thepwo.multicurrency.MultiCurrency;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PlayerData {
    private static final MultiCurrency plugin = MultiCurrency.getPlugin();
    private final UUID uuid;
    private final Map<String, BigDecimal> currencyData;

    public PlayerData(UUID uuid, Map<String, BigDecimal> currencyData) {
        this.uuid = uuid;
        this.currencyData = currencyData;
    }

    public static CompletableFuture<PlayerData> get(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> true).thenCompose(i -> {
            
        });
    }
}
