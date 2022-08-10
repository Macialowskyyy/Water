package me.macialowskyyy.water.task;

import me.macialowskyyy.water.uttil.Drinking;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitRunnable;
import me.macialowskyyy.water.main.Main;

import java.util.Map;


public class Task extends BukkitRunnable {

    private Main main;
    private int taskId;;

    public Task(Main main) {
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
        inst = this;
        for(Map.Entry<String, Double> str : Main.points.entrySet()) {
            if(Bukkit.getPlayer(str.getKey()).getGameMode().equals(GameMode.SURVIVAL)) {
                Drinking.removePoints(str.getKey(), 0.5);
                if(str.getValue() == 2) {
                    Bukkit.getPlayer(str.getKey()).sendMessage(ChatColor.RED + "Jestes bliski odwodnieniu! Napij sie gotowanej wody");
                }
            }
        }

    }

}
