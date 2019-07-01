package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.ToggleTroll;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class LagPlayerConnectionTroll implements ToggleTroll, Listener {

    // instance of the main class
    private BossTroll main;

    private List<UUID> playersAffected;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public LagPlayerConnectionTroll(final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
        this.playersAffected = new ArrayList<>();
    }

    @Override
    public void toggle(Player player) {
        if(playersAffected.contains(player.getUniqueId()))
            playersAffected.remove(player.getUniqueId());
        else playersAffected.add(player.getUniqueId());
    }

    @EventHandler
    public void handleAsyncPlayerChat(final AsyncPlayerChatEvent event) {
        if(!playersAffected.contains(event.getPlayer().getUniqueId()))
            return;

        final Player player = event.getPlayer();
        final String message = event.getMessage().replace("%", "%%");

        event.setCancelled(true);

        Bukkit.getScheduler().runTaskLater(this.main, () -> {
            player.chat(message);
        }, 20 * this.main.getConfigurationFile().getLagDelay());

    }

    @EventHandler
    public void handleBlockPlace(final BlockPlaceEvent event) {
        if(!playersAffected.contains(event.getPlayer().getUniqueId()))
            return;

        final Player player = event.getPlayer();
        final Block block = event.getBlock();

        event.setCancelled(true);

        Bukkit.getScheduler().runTaskLater(this.main, () -> {
            player.getWorld().getBlockAt(player.getLocation()).setType(block.getType());
        }, 20 * this.main.getConfigurationFile().getLagDelay());

    }

}