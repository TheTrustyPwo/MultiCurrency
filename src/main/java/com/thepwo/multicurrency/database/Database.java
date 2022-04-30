package com.thepwo.multicurrency.database;

import com.thepwo.multicurrency.MultiCurrency;
import com.thepwo.multicurrency.data.objects.PlayerData;

import java.util.concurrent.CompletableFuture;

public abstract class Database {
    protected final MultiCurrency multiCurrency;

    public Database(MultiCurrency multiCurrency) {
        this.multiCurrency = multiCurrency;
    }

    public abstract CompletableFuture<Boolean> connect();

    public abstract void close();

    public abstract CompletableFuture<PlayerData> get();

    public abstract
}
