package com.thepwo.multicurrency.database;

import com.thepwo.multicurrency.MultiCurrency;
import com.thepwo.multicurrency.database.objects.PlayerData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class Database {
    protected final MultiCurrency plugin;
    protected final Map<UUID, PlayerData> playerDataCache;

    public Database(MultiCurrency plugin) {
        this.plugin = plugin;
        this.playerDataCache = new HashMap<>();
    }

    public void cache(PlayerData playerData) {
        this.playerDataCache.put(playerData.uuid(), playerData);
    }

    public abstract CompletableFuture<Boolean> connect();

    public abstract void close();

    public abstract CompletableFuture<PlayerData> get(UUID uuid);

    public abstract void save(UUID uuid);
}
