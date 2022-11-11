package com.antienderchest.antienderchest;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiEnderChest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("AECが有効になりました。");
    }

    @Override
    public void onDisable() {
        getLogger().warning("AECが無効になりました。");
    }

    @EventHandler
    public void onOpenInv(InventoryOpenEvent event) {
        FileConfiguration config = getConfig();
        if(event.getInventory().getType() == InventoryType.ENDER_CHEST) {
            if(config.getBoolean("bypass_perm")) {
                if(!event.getPlayer().hasPermission("aec.bypass")) {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
            }
        }
    }
}
