package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.UniqueTroll;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This troll messes up the victim's inventory by dropping all items
 * out of the inventory.
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

    /**
     * Drops all items out of the players inventory.
     *
     * @param player The players whose items should be dropped.
     */
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