package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

/**
 * This class manages the case when a player switches his items in the hotbar.
 *
 * @author pxav
 */
public class PlayerHeldItemListener implements Listener {

    private BossTroll main;

    /**
     * Default constructor. Also registers the listener.
     * @param main The instance of the main class.
     */
    public PlayerHeldItemListener(BossTroll main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, this.main);
    }

    /**
     * This method is triggered when a player scrolls in his hotbar
     * or switches items in his hotbar.
     *
     * @param event The event object, which passes data
     *              like the exact player who switched items.
     */
    @EventHandler
    public void onPlayerHeldItem(PlayerItemHeldEvent event) {
        // interrupt the mini-gun tool so that the mini-gun stops
        // shooting when the player switches away from this item.
        this.main.getMiniGun().interrupt(event.getPlayer());
    }

}
