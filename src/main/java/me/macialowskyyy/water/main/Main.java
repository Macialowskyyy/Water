package me.macialowskyyy.water.main;

import me.macialowskyyy.water.events.*;
import me.macialowskyyy.water.papi.waters;
import me.macialowskyyy.water.recipes.BoiledWater;
import me.macialowskyyy.water.task.ActionBar;
import me.macialowskyyy.water.task.Task;
import me.macialowskyyy.water.uttil.Drinking;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public final class Main extends JavaPlugin {
    private static Main inst;
    public static HashMap<String, Double> points = new HashMap<>();

    private FileConfiguration dataConfig = null;
    private File configFile = null;
    private Task task;
    private ActionBar action;

    @Override
    public void onEnable() {
        inst = this;
        saveDefaultConfig();
        //Task
        this.task = new Task(this);
        this.task.runTaskTimerAsynchronously(this, 0, 3600);
        this.action = new ActionBar(this);
        this.action.runTaskTimerAsynchronously(this, 0, 120);
        new Drinking(this);





        BoiledWater.boiledWaterRecipe();
        Bukkit.getPluginManager().registerEvents(new onRight(), this);
        Bukkit.getPluginManager().registerEvents(new ConsumeEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onLeft(this), this);
        Bukkit.getPluginManager().registerEvents(new onJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new onHit(), this);
        Bukkit.getPluginManager().registerEvents(new onDeath(), this);
        Bukkit.getPluginManager().registerEvents(new onSprint(), this);
        //PlaceholderAPI
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new waters().register();
        } else {
            Bukkit.getPluginManager().disablePlugin(this);
            System.out.println("Brak PlaceholderAPI");
        }
        for(String s : getConfig().getConfigurationSection("waters").getKeys(false)) {
            if(Bukkit.getPlayer(s) != null) {
                points.put(s, Double.valueOf(getConfig().getString("waters." + s)));
            }
        }
    }

    @Override
    public void onDisable() {
        for(Map.Entry<String, Double> off : points.entrySet()) {
            getConfig().set("waters." + off.getKey(), off.getValue());
        }
        saveConfig();
    }

    public static Main getInst() {
        return inst;
    }


    public void reloadConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.getDataFolder(), "data.yml");
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = this.getResource("data.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }
    public @NotNull FileConfiguration getConfig() {
        if (this.dataConfig == null)
            reloadConfig();
        return  this.dataConfig;
    }
    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null)
            return;
        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            this.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
        }
    }
    public void saveDefaultConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.getDataFolder(), "data.yml");
        if (!this.configFile.exists()) {
            this.saveResource("data.yml", false);
        }
    }



}
