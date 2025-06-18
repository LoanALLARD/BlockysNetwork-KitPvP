package fr.dwade.blokcyskp.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dwade.blokcyskp.Menu.KitMenu;

public class Kit implements CommandExecutor {

	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande est réservée aux joueurs.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length > 0) {
            KitMenu.openKitMenu(player);
        } else {
            player.sendMessage("Usage: /kit");
        }

        return true;
    }

}
