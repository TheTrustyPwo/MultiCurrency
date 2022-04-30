package com.thepwo.multicurrency.database.impl.yaml;

import com.thepwo.multicurrency.MultiCurrency;
import com.thepwo.multicurrency.database.Database;
import com.thepwo.multicurrency.database.objects.PlayerData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class YamlDatabase extends Database {
    private final String fileName;
    private File file;
    private YamlConfiguration data;

    public YamlDatabase(MultiCurrency plugin) {
        super(plugin);
        this.fileName = this.plugin.getConfig().getString("database.yaml.file");
    }

    @Override
    public CompletableFuture<Boolean> connect() {
        return CompletableFuture.supplyAsync(() -> {
            this.file = new File(this.plugin.getDataFolder(), this.fileName);

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    return false;
                }
            }

            this.data = YamlConfiguration.loadConfiguration(this.file);
            return true;
        });
    }

    @Override
    public void close() {
        saveFile();
    }

    @Override
    public CompletableFuture<PlayerData> get(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            PlayerData playerData = this.playerDataCache.get(uuid);
            if (playerData != null) return playerData;

            Map<String, Double> currencyData = new HashMap<>();
            ConfigurationSection playerStorage = this.data.getConfigurationSection(uuid.toString());
            if (playerStorage != null) {
                for (String currency : playerStorage.getKeys(false)) {
                    currencyData.put(currency.toLowerCase(), playerStorage.getDouble(currency));
                }
            }

            playerData = new PlayerData(uuid, currencyData);
            cache(playerData);
            return playerData;
        });
    }

    @Override
    public void save(UUID uuid) {
        CompletableFuture.runAsync(() -> {
            PlayerData playerData = this.playerDataCache.get(uuid);
            if (playerData == null) return;

            ConfigurationSection playerStorage = this.data.getConfigurationSection(playerData.uuid().toString());
            if (playerStorage == null) playerStorage = this.data.createSection(playerData.uuid().toString());

            for (String currency : playerData.currencyData().keySet()) {
                playerStorage.set(currency.toLowerCase(), playerData.currencyData().get(currency));
            }

            saveFile();
        });
    }

    private void saveFile() {
        try {
            this.data.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
