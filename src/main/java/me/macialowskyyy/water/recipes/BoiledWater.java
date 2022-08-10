package me.macialowskyyy.water.recipes;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class BoiledWater {
    public static ItemStack result;

    public static void boiledWaterRecipe() {
        result = new ItemStack(Material.POTION);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Zagotowana " + ChatColor.AQUA + "woda");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Zagotowana woda bez bakterii i zarazkow.");
        lore.add(ChatColor.GRAY + "");
        lore.add(ChatColor.GRAY + "Korzysci:");
        lore.add(ChatColor.AQUA + " 2.0 Wody");
        lore.add(ChatColor.RED + " 2 sek Regeneracja");
        lore.add(ChatColor.RED + " 0.5 Jedzenia");
        meta.setLore(lore);
        PotionMeta potion = (PotionMeta) meta;
        potion.setColor(Color.AQUA);
        potion.setBasePotionData(new PotionData(PotionType.WATER));
        result.setItemMeta(potion);
        ItemStack water = new ItemStack(Material.POTION);
        ItemMeta im = water.getItemMeta();
        PotionMeta pw = (PotionMeta) im;
        pw.setBasePotionData(new PotionData(PotionType.WATER));
        water.setDurability(Short.parseShort("373"));
        water.setItemMeta(pw);
        FurnaceRecipe recipe = new FurnaceRecipe(result, water.getType());
        recipe.setCookingTime(100);
        recipe.setExperience(0);
        getServer().addRecipe(recipe);
    }
}
