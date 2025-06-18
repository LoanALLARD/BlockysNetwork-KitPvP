package fr.dwade.blokcyskp;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable () {
		System.out.println("Blockys-Network - Activation du plugin Kit-PvP");
	}
	
	@Override
	public void onDisable () {
		System.out.println("Blockys-Network - Desactivation du plugin Kit-PvP");
	}
	
}
