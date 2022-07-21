package xyz.clxrity.simplehealing;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.clxrity.simplehealing.commands.FeedCommand;
import xyz.clxrity.simplehealing.commands.HealCommand;


public final class SimpleHealing extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
