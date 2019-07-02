package de.pxav.bosstroll.items;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This class is used to build the inventory to troll
 * the entire server.
 *
 * @author pxav
 */
public class ServerTrollInventory {

    // instance of the main class
    private BossTroll main;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public ServerTrollInventory(final BossTroll main) {
        this.main = main;
    }

    /**
     * Opens the inventory and automatically registers the needed item listeners.
     *
     * @param player The player who should see the inventory.
     */
    public void open(final Player player) {

        final String title = "§cTroll Server";
        final Inventory inventory = Bukkit.createInventory(null, 27, title);

        // set fill material to the inventory
        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, new ItemBuilder().buildFillMaterial());

        inventory.setItem(10, new ItemBuilder(Material.ENDER_PEARL)
                .setDisplayName("§cTeleport all players to you")
                .setLore(new String[]{
                        "§7All players who are currently online",
                        "§7will get teleported to your location",
                        "§7immediately"
                })
                .addListener(player, title, event
                        -> {
                    event.setCancelled(true);
                    main.getTeleportAllTroll().execute(player, false);
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(11, new ItemBuilder(Material.EMERALD)
                .setDisplayName("§cSend HackMessage")
                .setLore(new String[]{
                        "§7Will broadcast titles, sounds and",
                        "§7messages ",
                        "§7"
                })
                .addListener(player, title, event
                        -> {
                    event.setCancelled(true);
                    main.getHackMessageTroll().execute(player, true);
                    player.closeInventory();
                })
                .build()
        );

        // finally open the inventory to the player.
        player.openInventory(inventory);

    }

}