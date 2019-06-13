package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class PlayerQuitListener implements Listener {

    private BossTroll main;

    /**
     * Default constructor. It's recommended to execute this on every server startup.
     * This constructor also registeres the listener.
     *
     * @param main The instance of the main class.
     */
    public PlayerQuitListener(final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
    }

    @EventHandler
    public void handleEvent(final PlayerQuitEvent event) {

        final Player player = event.getPlayer();

        if (this.main.getPlayerInfo().getPlayersTrolling().containsKey(player.getUniqueId())) {
            this.main.getListenerUtil().unregisterListener(player);
            this.main.getPlayerInfo().getPlayersTrolling().remove(player.getUniqueId());
        } else if (this.main.getPlayerInfo().getPlayersTrolling().containsValue(player.getUniqueId())) {
            final Player trolling = Bukkit.getPlayer(this.main.getPlayerInfo().getTrollingPlayers().get(player.getUniqueId()));
            this.main.getListenerUtil().unregisterListener(trolling);
            this.main.getPlayerInfo().getPlayersTrolling().remove(trolling.getUniqueId());
            this.main.getPlayerInfo().getTrollingPlayers().remove(player.getUniqueId());
            trolling.sendMessage(this.main.getPrefix() + "§cThe player you are trolling left the server.");
            trolling.closeInventory();
        }
    }

}