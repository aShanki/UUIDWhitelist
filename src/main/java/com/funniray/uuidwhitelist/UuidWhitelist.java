package com.funniray.uuidwhitelist;

import com.funniray.uuidwhitelist.commands.UuidWhitelistCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class UuidWhitelist extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("uuidwhitelist").setExecutor(new UuidWhitelistCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
