package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class InventoryCloseListener implements Listener {

    private BossTroll main;

    /**
     * Default constructor. It's recommended to execute this on every server startup.
     * This constructor also registeres the listener.
     *
     * @param main The instance of the main class.
     */
    public InventoryCloseListener(final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
    }

    @EventHandler
    public void handleEvent(final InventoryCloseEvent event) {

        if(!(event.getPlayer() instanceof Player))
            return;

        final Player player = (Player) event.getPlayer();

        this.main.getListenerUtil().unregisterListener(player);
        this.main.getPlayerInfo().getPlayersTrolling().remove(player.getUniqueId());
        this.main.getPlayerInfo().getTrollingPlayers().remove(player.getUniqueId());

    }

}