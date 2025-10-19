package com.BlockysKP;

import com.BlockysKP.Commands.SetSpawn;
import org.bukkit.plugin.java.JavaPlugin;
import com.BlockysKP.Commands.Kit;
import com.BlockysKP.Menu.KitMenu;

public class Main extends JavaPlugin {

    @Override
    public void onEnable () {
        // Enable message
        System.out.println("Blockys-Network - Activation du plugin Kit-PvP");

        // Create config.yml if it does not exist
        saveDefaultConfig();

        // Records commands
        getCommand("kit").setExecutor(new Kit());
        getCommand("setspawn").setExecutor(new SetSpawn(this));

        // Records events
        getServer().getPluginManager().registerEvents(new KitMenu(), this);
        getServer().getPluginManager().registerEvents(new Listeners(this), this);
    }

    @Override
    public void onDisable () {
        // Disable message
        System.out.println("Blockys-Network - Desactivation du plugin Kit-PvP");
    }

}
