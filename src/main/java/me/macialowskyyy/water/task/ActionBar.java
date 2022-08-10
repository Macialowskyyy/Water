package me.macialowskyyy.water.task;

import me.macialowskyyy.water.uttil.Drinking;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.macialowskyyy.water.main.Main;

public class ActionBar extends BukkitRunnable {

    private Main main;
    private int taskId;;

    public  ActionBar(Main main) {
        this.main = main;
        this.taskId = this.getTaskId();
    }
    public int getTaskId() {
        return taskId;
    }
    private static Task inst;

    @Override
    public void run()
    {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                double z = Drinking.getPoints(player.getName());
                String kolor = ChatColor.BLACK + "0";

                if (z < 0) {
                    Drinking.setPoints(player.getName(), 0.0);
                }
                if (z >= 18.5) {
                    kolor = ChatColor.GREEN + String.valueOf(z);
                } else if (z >= 15.5) {
                    kolor = ChatColor.DARK_GREEN + String.valueOf(z);
                } else if (z >= 10.5) {
                    kolor = ChatColor.YELLOW + String.valueOf(z);
                } else if (z >= 5.5) {
                    kolor = ChatColor.GOLD + String.valueOf(z);
                } else if (z >= 2.0) {
                    kolor = ChatColor.RED + String.valueOf(z);

                } else if (z >= 0.5) {
                    kolor = ChatColor.DARK_RED + String.valueOf(z);
                    player.setFoodLevel(player.getFoodLevel() - 1);
                } else if (z == 0.0) {
                    player.setFoodLevel(player.getFoodLevel() - 1);

                }
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "Twoj poziom nawodnienia wynosi: " + kolor + ChatColor.AQUA + " czyli " + (Drinking.getPoints(player.getName()) * 100) / 20 + "%"));
            }
        }

    }
}
