package com.BlockysKP.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnUtils {

    private final JavaPlugin plugin;

    public SpawnUtils(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void teleportToSpawn(Player player) {
        String worldName = plugin.getConfig().getString("spawn.world");
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            player.sendMessage("§cLe monde de spawn n'existe pas !");
            return;
        }

        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
        float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");

        Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
        player.teleport(spawnLocation);
        player.sendMessage("§aTéléporté au spawn !");
    }
}
