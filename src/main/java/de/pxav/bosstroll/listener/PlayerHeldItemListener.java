package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class PlayerHeldItemListener implements Listener {

    private BossTroll main;

    public PlayerHeldItemListener(BossTroll main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, this.main);
    }

    @EventHandler
    public void onPlayerHeldItem(PlayerItemHeldEvent event) {
        this.main.getMiniGun().interrupt(event.getPlayer());
    }

}
