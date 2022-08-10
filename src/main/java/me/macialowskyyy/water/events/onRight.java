package me.macialowskyyy.water.events;

import me.macialowskyyy.water.recipes.BoiledWater;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.Objects;

public class onRight implements Listener {
    @EventHandler
    public void onRight(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            if (p.isSneaking()) {
                if (Objects.requireNonNull(e.getClickedBlock()).getState() instanceof Furnace) {
                    e.setCancelled(true);
                    Block b = e.getClickedBlock();
                    Furnace fur = (Furnace) b.getState();
                    ItemStack water = new ItemStack(Material.POTION);
                    ItemMeta im = water.getItemMeta();
                    PotionMeta pw = (PotionMeta) im;
                    pw.setBasePotionData(new PotionData(PotionType.WATER));
                    water.setItemMeta(pw);
                    if (fur.getInventory().getResult() != null || fur.getInventory().getSmelting() != null) {
                        if (fur.getInventory().getResult() != null && fur.getInventory().getResult().getType() == BoiledWater.result.getType()) {
                            if (fur.getInventory().getSmelting() == null) {
                                p.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "Twoja woda jest zagotowana"));
                            }
                        }
                        if (fur.getInventory().getSmelting() != null && fur.getInventory().getSmelting().getType() == water.getType()) {
                            if (fur.getInventory().getResult() == null) {
                                p.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "Twoja woda ma " + ChatColor.RED + fur.getCookTime() + ChatColor.GREEN + " stopni, czyli " + fur.getCookTime() + "% do ugotowania"));
                            }
                        }

                    }


                }

            }
        } catch (NullPointerException ev) {

        }
    }
}
