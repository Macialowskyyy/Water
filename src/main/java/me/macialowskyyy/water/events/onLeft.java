package me.macialowskyyy.water.events;

import me.macialowskyyy.water.uttil.Drinking;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import me.macialowskyyy.water.main.Main;

public class onLeft implements Listener {
    private final Main plugin;

    public onLeft(Main m) {
        plugin = m;
    }
    @EventHandler
    public void onLeft(PlayerQuitEvent e) {

        plugin.getConfig().set("waters." + e.getPlayer().getName(), Drinking.getPoints(e.getPlayer().getName()));
        plugin.saveConfig();
        Main.points.remove(e.getPlayer().getName());
    }
}
