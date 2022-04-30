package com.thepwo.multicurrency;

import com.thepwo.multicurrency.database.Database;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class MultiCurrency extends JavaPlugin {
    private static MultiCurrency plugin;
    private Database database;

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MultiCurrency getPlugin() {
        return plugin;
    }

    public Database getDatabase() {
        return database;
    }
}
