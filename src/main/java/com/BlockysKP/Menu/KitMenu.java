package com.BlockysKP.Menu;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitMenu implements Listener {

    private static HashMap<UUID, Boolean> joueurKitPris = new HashMap<>();

    public static void setJoueurKitPris(HashMap<UUID, Boolean> joueurKitPris) {
        KitMenu.joueurKitPris = joueurKitPris;
    }

    public static HashMap<UUID, Boolean> getJoueurKitPris() {
        return joueurKitPris;
    }

    public static void openKitMenu(Player player) {
        // Vérifie si le joueur a déjà pris un kit
        if (joueurKitPris.getOrDefault(player.getUniqueId(), false)) {
            player.sendMessage("§cTu as déjà pris un kit !");
            player.closeInventory();
            return;
        }

        Inventory inv = Bukkit.createInventory(null, 9, "Sélectionne ton kit");

        inv.setItem(2, createItem(Material.IRON_SWORD, "§cGuerrier"));
        inv.setItem(4, createItem(Material.BOW, "§aArcher"));
        inv.setItem(6, createItem(Material.BLAZE_ROD, "§bMage"));

        player.openInventory(inv);
    }

    private static ItemStack createItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Sélectionne ton kit")) return;

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;

        switch (clicked.getType()) {
            case IRON_SWORD:
                giveKitGuerrier(player);
                break;
            case BOW:
                giveKitArcher(player);
                break;
            case BLAZE_ROD:
                giveKitMage(player);
                break;
            default:
                break;
        }

        // Marque le joueur comme ayant pris un kit
        joueurKitPris.put(player.getUniqueId(), true);

        player.closeInventory();
    }

    private void giveKitGuerrier(Player player) {
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getInventory().setHeldItemSlot(0);
        player.sendMessage("§aTu as reçu le kit Guerrier !");
    }

    private void giveKitArcher(Player player) {
        player.getInventory().addItem(new ItemStack(Material.BOW), new ItemStack(Material.ARROW, 32));
        player.getInventory().setHeldItemSlot(0);
        player.sendMessage("§aTu as reçu le kit Archer !");
    }

    private void giveKitMage(Player player) {
        player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD));
        player.getInventory().setHeldItemSlot(0);
        player.sendMessage("§aTu as reçu le kit Mage !");
    }
}
