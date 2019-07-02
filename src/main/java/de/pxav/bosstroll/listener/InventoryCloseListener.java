package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * This listener class manages what should happen
 * when a player closes their inventory.
 *
 * @author pxav
 */
public class InventoryCloseListener implements Listener {

    // instance of the main class.
    private BossTroll main;

    /**
     * Default constructor. It's recommended to execute this on every server startup.
     * This constructor also registers the listener.
     *
     * @param main The instance of the main class.
     */
    public InventoryCloseListener(final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
    }

    /**
     * This method is called when a player closes his inventory.
     * @param event The event object, which passes data like the name of the closed inventory.
     */
    @EventHandler
    public void handleInventoryClose(final InventoryCloseEvent event) {

        // check if a player closes his inventory.
        if(!(event.getPlayer() instanceof Player))
            return;

        final Player player = (Player) event.getPlayer();

        // unregister the listeners that are active for the current player.
        this.main.getListenerUtil().unregisterListener(player);

    }

}