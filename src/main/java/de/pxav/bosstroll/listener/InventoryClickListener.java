package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

/**
 * This listener class is triggered when a player clicks any item in any inventory.
 *
 * @author pxav
 */
public class InventoryClickListener implements Listener {

    // instance of the main class
    private BossTroll main;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public InventoryClickListener(final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
    }

    /**
     * This method is called when a player clicks in any inventory.
     *
     * @param event The event object, which passes data like the display name of the clicked item.
     */
    @EventHandler
    public void handleItemClick(final InventoryClickEvent event) {

        // check if the player actually clicks an item and it has a display name.
        if(event.getInventory() == null
            || event.getCurrentItem() == null
            || event.getCurrentItem().getItemMeta() == null)
                return;

        // fire all potential listeners added by the ItemBuilder
        this.main.getListenerUtil().fire(event);

    }

}