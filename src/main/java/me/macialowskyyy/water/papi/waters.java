package me.macialowskyyy.water.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.macialowskyyy.water.uttil.Drinking;
import org.bukkit.entity.Player;

public class waters extends PlaceholderExpansion {



    @Override
    public String getIdentifier()
    {
        return "water";
    }

    @Override
    public String getAuthor()
    {
        return "Macialowskyyy";
    }

    @Override
    public String getVersion()
    {
        return "1.90";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null) {
            return "null";
        }

        switch (identifier) {

            case "player_waters":
            case "waters":
                return player.isOnline() ? String.valueOf(Drinking.getPoints(player.getName())) : "null";

            default:
                return null;
        }

    }
}
