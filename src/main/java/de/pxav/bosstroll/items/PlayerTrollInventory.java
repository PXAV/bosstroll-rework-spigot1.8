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
 * A class description goes here.
 *
 * @author pxav
 */
public class PlayerTrollInventory {

    // instance of the main class
    private BossTroll main;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public PlayerTrollInventory(final BossTroll main) {
        this.main = main;
    }

    public void prepare() {

    }

    public void open(final Player player) {

        final Player target = Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()));
        final Inventory inventory = Bukkit.createInventory(null, 54, "§cTROLL " + target.getName());

        inventory.setItem(4, new SkullBuilder()
                .setDisplayName("§c" + target.getName())
                .setLore(new String[] {
                        "§7is the player you are currently trolling.",
                })
                .setSkullOwner(target.getName())
                .build());

        inventory.setItem(10, new ItemBuilder(Material.FLINT_AND_STEEL)
                .setDisplayName("§cRing of fire")
                .setLore(new String[] {
                        "§7Spawns fire blocks around the",
                        "§7player in a range of 3 blocks."
                })
                .addListener(player, event -> {
                    event.setCancelled(true);
                    this.main.getFireRingTroll().execute(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                })
                .build()
        );

        inventory.setItem(11, new ItemBuilder(Material.DIRT)
                .setDisplayName("§cDrop items")
                .setLore(new String[] {
                        "§7Drops all items of the players'",
                        "§7inventory onto the ground."
                })
                .addListener(player, event -> {
                    event.setCancelled(true);
                    this.main.getDropInventoryTroll().execute(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                })
                .build()
        );

        inventory.setItem(12, new ItemBuilder(Material.PAPER)
                .setDisplayName("§cSolve Math problem")
                .setLore(new String[] {
                        "§7Picks a random math problem to solve.",
                        "§7If the player does not solve it within",
                        "§7a certain time, they will get killed."
                })
                .addListener(player, event -> {
                    event.setCancelled(true);
                    this.main.getMathTroll().begin(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                })
                .build()
        );

        player.openInventory(inventory);


    }

}