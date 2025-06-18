package fr.dwade.blokcyskp;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.jline.internal.Configuration;
import org.bukkit.entity.Player;

public class CreateMap implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    if (cmd.getName().equalsIgnoreCase("setspawn")) {
	        if (!(sender instanceof Player)) return false;
	        Player player = (Player) sender;

	        Location loc = player.getLocation();

	        getConfig().set("spawn.world", loc.getWorld().getName());
	        getConfig().set("spawn.x", loc.getX());
	        getConfig().set("spawn.y", loc.getY());
	        getConfig().set("spawn.z", loc.getZ());
	        getConfig().set("spawn.yaw", loc.getYaw());
	        getConfig().set("spawn.pitch", loc.getPitch());

	        saveConfig();
	        player.sendMessage("§aSpawn défini !");
	        return true;
	    }

	    return false;
	}
}
