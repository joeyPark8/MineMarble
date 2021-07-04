package com.joey.minemarble;

import org.bukkit.plugin.java.JavaPlugin;

import static com.joey.minemarble.MarbleCommand.register;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println(this.getName() + " is activated");

        getServer().getPluginManager().registerEvents(new MarbleEvent(), this);

        register();
    }

    @Override
    public void onDisable() {
        System.out.println(this.getName() + " is deactivated");
    }
}
