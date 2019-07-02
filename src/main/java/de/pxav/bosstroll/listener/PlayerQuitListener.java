package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This class manages the case when a player quits the server.
 *
 * @author pxav
 */
public class PlayerQuitListener implements Listener {

    private BossTroll main;

    /**
     * Default constructor. It's recommended to execute this on every server startup.
     * This constructor also registers the listener.
     *
     * @param main The instance of the main class.
     */
    public PlayerQuitListener(final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
    }

    /**
     * This method is called when a player quits the server.
     *
     * @param event the event object, which passes data
     *              like the player who left the server.
     */
    @EventHandler
    public void handleEvent(final PlayerQuitEvent event) {

        final Player player = event.getPlayer();

        if (this.main.getPlayerInfo().getPlayersTrolling().containsKey(player.getUniqueId())) {
            this.main.getListenerUtil().unregisterListener(player);
            this.main.getPlayerInfo().getPlayersTrolling().remove(player.getUniqueId());
        } else if (this.main.getPlayerInfo().getPlayersTrolling().containsValue(player.getUniqueId())) {

            // if a troll victim leaves the server, close the troll inventory
            // and notify the player who trolls the victim.

            this.main.getPlayerInfo().getPlayersTrolling().forEach((key, value) -> {
                if (value.toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                    final Player trolling = Bukkit.getPlayer(key);
                    this.main.getListenerUtil().unregisterListener(trolling);
                    this.main.getPlayerInfo().getPlayersTrolling().remove(trolling.getUniqueId());
                    trolling.sendMessage(this.main.getPrefix() + "§cThe player you were trolling left the server.");
                    trolling.closeInventory();
                }
            });

        }

        // interrupt the mini-gun tool so that the mini-gun stops
        // shooting when the player quits the server.
        this.main.getMiniGun().interrupt(player);

    }

}