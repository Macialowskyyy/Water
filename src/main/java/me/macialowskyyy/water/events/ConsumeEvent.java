package me.macialowskyyy.water.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;
import me.macialowskyyy.water.uttil.Drinking;

public class ConsumeEvent implements Listener {
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode().equals(GameMode.SURVIVAL) || p.getGameMode().equals(GameMode.ADVENTURE)) {
            if (e.getItem().getType() == Material.POTION) {
                PotionType potionType = ((PotionMeta) e.getItem().getItemMeta()).getBasePotionData().getType();
                if (potionType == PotionType.WATER) {
                    if (e.getItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Zagotowana " + ChatColor.AQUA + "woda")) {
                        Drinking.onBoiledDrinking(p);
                    } else {
                        Drinking.onDrinkingScieki(p);
                    }
                }
            }
            if (e.getItem().getType() == Material.MELON_SLICE) {
                Drinking.addPoints(p, 1.5);
            }
            if (e.getItem().getType() == Material.APPLE) {
                Drinking.addPoints(p, 1.0);
            }
            if (e.getItem().getType() == Material.GOLDEN_APPLE) {
                Drinking.addPoints(p, 1.5);
            }
            if (e.getItem().getType() == Material.ENCHANTED_GOLDEN_APPLE) {
                Drinking.addPoints(p, 4.0);
            }
            if (e.getItem().getType() == Material.GLISTERING_MELON_SLICE) {
                Drinking.addPoints(p, 2.0);
            }
            if (e.getItem().getType() == Material.CARROT) {
                Drinking.addPoints(p, 0.5);
            }
            if (e.getItem().getType() == Material.GOLDEN_CARROT) {
                Drinking.addPoints(p, 1.0);
            }
            if (e.getItem().getType() == Material.SWEET_BERRIES) {
                Drinking.addPoints(p, 0.5);
            }
            if (e.getItem().getType() == Material.GLOW_BERRIES) {
                Drinking.addPoints(p, 0.5);
            }
            if (e.getItem().getType() == Material.HONEY_BOTTLE) {
                Drinking.removePoints(p.getName(), 0.5);
            }
        }
    }
}
