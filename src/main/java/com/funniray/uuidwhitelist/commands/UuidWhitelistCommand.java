package com.funniray.uuidwhitelist.commands;

import com.funniray.uuidwhitelist.UuidWhitelist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UuidWhitelistCommand implements CommandExecutor {
    UuidWhitelist plugin;

    public UuidWhitelistCommand(UuidWhitelist plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /uuidwhitelist <uuid>");
            return true;
        }

        UUID uuid;

        try {
            uuid = UUID.fromString(args[0]);
        } catch (IllegalArgumentException e) {
            sender.sendMessage(ChatColor.RED + "Invalid UUID");
            return true;
        }
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        PlayerProfile profile = player.getPlayerProfile();

        if (profile.isComplete()) {
            sender.sendMessage("Added "+player.getName()+" ("+player.getUniqueId()+") to the whitelist.");
            player.setWhitelisted(true);
            return true;
        }

        profile.update().thenAcceptAsync((profile1) -> {
            sender.sendMessage("Added "+player.getName()+" ("+player.getUniqueId()+") to the whitelist.");
            player.setWhitelisted(true);
        }, runnable -> Bukkit.getScheduler().runTask(plugin, runnable));

        return true;
    }
}
