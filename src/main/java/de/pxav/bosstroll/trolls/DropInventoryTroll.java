package de.pxav.bosstroll.trolls;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class DropInventoryTroll implements UniqueTroll {

    // instance of the main class
    private BossTroll main;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public DropInventoryTroll(final BossTroll main) {
        this.main = main;
    }

    @Override
    public void execute(Player player) {
        final Inventory playerInventory = player.getInventory();
        for (ItemStack current : playerInventory.getContents()) {
            if(current != null)
                player.getWorld().dropItem(player.getLocation(), current);
        }
        playerInventory.clear();
    }
}