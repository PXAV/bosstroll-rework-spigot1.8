package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

/**
 * A class description goes here.
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

    @EventHandler
    public void handleItemClick(final InventoryClickEvent event) {
        if(event.getInventory() == null
            || event.getCurrentItem() == null
            || event.getCurrentItem().getItemMeta() == null)
                return;
        this.main.getListenerUtil().fire(event);
    }

}