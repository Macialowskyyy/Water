package me.macialowskyyy.water.events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import me.macialowskyyy.water.uttil.Drinking;

public class onDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Drinking.setPoints(e.getEntity().getName(), 20.0);
        onHit.hit.put(e.getEntity().getName(), 0);
        onSprint.sprints.put(e.getEntity().getName(), 0);
        if(Drinking.getPoints(e.getEntity().getName()) == 0) {
            e.getEntity().sendMessage(ChatColor.RED + "Umarles z powodu odwodnienia!");
        }
    }
}
