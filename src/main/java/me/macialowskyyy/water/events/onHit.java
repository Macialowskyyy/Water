package me.macialowskyyy.water.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import me.macialowskyyy.water.uttil.Drinking;

import java.util.HashMap;

public class onHit implements Listener {

    public static HashMap<String, Integer> hit = new HashMap<>();

    @EventHandler
    public void onHits(EntityDamageEvent e) {
        Entity p = e.getEntity();
        if(p instanceof Player) {
            if (hit.get(p.getName()) != null) {
                if (hit.get(p.getName()) == 6) {
                    hit.put(p.getName(), 1);
                    Drinking.removePoints(p.getName(), 0.5);
                } else {
                    int nawod = hit.get(p.getName());
                    nawod++;
                    hit.put(p.getName(), nawod);
                }
            } else {
                hit.put(p.getName(), 1);
            }
        }

    }
}
