package me.macialowskyyy.water.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import me.macialowskyyy.water.main.Main;

public class onJoin implements Listener {
    private final Main plugin;

    public onJoin(Main m) {
        plugin = m;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        plugin.reloadConfig();
        if (plugin.getConfig().contains("waters." + e.getPlayer().getName())) {
            Main.points.put(e.getPlayer().getName(), Double.valueOf(plugin.getConfig().getString("waters." + e.getPlayer().getName())));
        } else {
            Main.points.put(e.getPlayer().getName(), 20.0);
            plugin.getConfig().set("waters." + e.getPlayer().getName(), 20.0);
            plugin.saveConfig();
        }
    }
}
