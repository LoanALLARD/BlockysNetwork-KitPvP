package com.BlockysKP;

import com.BlockysKP.Menu.KitMenu;
import com.BlockysKP.Utils.SpawnUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

import static com.BlockysKP.Utils.EventUtils.cancel;

public class Listeners implements Listener {

    private final SpawnUtils spawnUtils;

    public Listeners(JavaPlugin plugin) {
        this.spawnUtils = new SpawnUtils(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Récupérer la map actuelle
        HashMap<UUID, Boolean> map = KitMenu.getJoueurKitPris();

        // Ajouter ce joueur comme n’ayant pas pris de kit
        map.put(player.getUniqueId(), false);

        // Mettre à jour la map via la méthode
        KitMenu.setJoueurKitPris(map);

        // Teleport au spawn
        spawnUtils.teleportToSpawn(player);

        // Clear inventaire
        player.getInventory().clear();

        // Set player gamemode to adventure
        event.getPlayer().setGameMode(GameMode.ADVENTURE);

        // Supprimer message join et broadcast
        event.setJoinMessage(null);
        Bukkit.broadcastMessage("§f[§a+§f] §7" + player.getName());
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = player.getKiller();
        String killerName = (killer != null) ? killer.getName() : "l'environnement";

        player.sendMessage("§f[§bKP§f] §7Tu as été tué par §c" + killerName + " §7!");

        // Teleport au spawn
        spawnUtils.teleportToSpawn(player);

        // Reset kit via setJoueurKitPris
        HashMap<UUID, Boolean> map = KitMenu.getJoueurKitPris();
        map.put(player.getUniqueId(), false);
        KitMenu.setJoueurKitPris(map);

        // Clear inventaire et drops
        player.getInventory().clear();
        event.getDrops().clear();
        event.setDroppedExp(0);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void cancelDropItem(PlayerDropItemEvent event) {
        cancel(event);
    }

    @EventHandler
    public void cancelArmorStandPickup(PlayerArmorStandManipulateEvent event) {
        cancel(event);
    }

    @EventHandler
    public void cancelTheStrike(EntityDamageByEntityEvent event) {
        // Only handle cases where both entities are players
        if (!(event.getEntity() instanceof Player)) return;
        Player victim = (Player) event.getEntity();

        if (!(event.getDamager() instanceof Player)) return;
        Player attacker = (Player) event.getDamager();

        // Get the map that stores which players have chosen a kit
        HashMap<UUID, Boolean> map = KitMenu.getJoueurKitPris();

        // If the attacker hasn't chosen a kit → cancel the hit
        if (!map.getOrDefault(attacker.getUniqueId(), false)) {
            event.setCancelled(true);
            return;
        }

        // Prevent hitting players who haven't chosen a kit either
        if (!map.getOrDefault(victim.getUniqueId(), false)) {
            event.setCancelled(true);
        }
    }
}
