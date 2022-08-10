package me.macialowskyyy.water.uttil;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import me.macialowskyyy.water.main.Main;

import java.util.Map;

public class Drinking {
    private static Main plugin;
    public Drinking(Main main) {
        this.plugin = main;
    }

    public static void onBoiledDrinking(Player p) {
        addPoints(p,2.0);
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));
        p.setFoodLevel(p.getFoodLevel() + 1);
    }

    public static void onDrinkingScieki(Player p) {
        addPoints(p,1.0);
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 2));
    }

    public static void addPoints(Player p, Double d) {
        if(getPoints(p.getName())+d <= 20) {
            setPoints(p.getName(), getPoints(p.getName()) + d);
        } else {
            setPoints(p.getName(), 20.0);
        }
    }

    public static void setPoints(String p, Double d) {
        Main.points.put(p, d);
    }

    public static void removePoints(String p, Double d) {
        if(getPoints(p) > 0.0) {
            setPoints(p, getPoints(p) - d);
        }
        if(getPoints(p) <= 2.0) {
            Bukkit.getPlayer(p).sendMessage(ChatColor.RED + "Jestes bliski odwodnieniu! Napij sie najlepiej gotowanej wody");
        }
    }
    public static void addAll(Double d) {
        for(Map.Entry<String, Double> str : Main.points.entrySet()) {
            setPoints(str.getKey(), getPoints(str.getKey())+d);
        }
    }
    public static void removeAll(Double d) {
        for(Map.Entry<String, Double> str : Main.points.entrySet()) {
            setPoints(str.getKey(), getPoints(str.getKey())-d);
        }
    }
    public static void setAll(Double d) {
        for(Map.Entry<String, Double> str : Main.points.entrySet()) {
            setPoints(str.getKey(), d);
        }
    }
    public static double getPoints(String p) {
        for(Map.Entry<String, Double> str : Main.points.entrySet()) {
            if(str.getKey().equals(p)) {
                return str.getValue();
            }
        }
        return 0;
    }
}
