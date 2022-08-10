package me.macialowskyyy.water.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import me.macialowskyyy.water.uttil.Drinking;

import java.util.HashMap;

public class onSprint implements Listener {

    public static HashMap<String, Integer> sprints = new HashMap<>();

    @EventHandler
    public void onSprint(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode().equals(GameMode.SURVIVAL)) {
            if(p.isSprinting()) {
                if (sprints.get(p.getName()) != null) {
                    if (sprints.get(p.getName()) == 330) {
                        sprints.put(p.getName(), 1);
                        Drinking.removePoints(p.getName(), 0.5);
                    } else {
                        int nawod = sprints.get(p.getName());
                        nawod++;
                        sprints.put(p.getName(), nawod);
                    }
                } else {
                    sprints.put(p.getName(), 1);
                }
            }
        }
    }
}
