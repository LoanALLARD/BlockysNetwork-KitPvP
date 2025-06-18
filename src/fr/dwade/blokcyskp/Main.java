package fr.dwade.blokcyskp;

import org.bukkit.plugin.java.JavaPlugin;

import fr.dwade.blokcyskp.Commands.Kit;
import fr.dwade.blokcyskp.Commands.CreateMap;
import fr.dwade.blokcyskp.Menu.KitMenu;

public class Main extends JavaPlugin {

	@Override
	public void onEnable () {
		System.out.println("Blockys-Network - Activation du plugin Kit-PvP");
		getCommand("kit").setExecutor(new Kit());
		getCommand("setspawn").setExecutor(new CreateMap(this));
		getServer().getPluginManager().registerEvents(new KitMenu(), this);
	}
	
	@Override
	public void onDisable () {
		System.out.println("Blockys-Network - Desactivation du plugin Kit-PvP");
	}
	
}
