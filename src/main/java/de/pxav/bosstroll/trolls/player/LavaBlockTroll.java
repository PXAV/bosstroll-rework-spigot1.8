package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.ToggleTroll;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class LavaBlockTroll implements ToggleTroll, Listener {

    // instance of the main class
    private BossTroll main;

    private List<UUID> playersAffected;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public LavaBlockTroll(final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
        this.playersAffected = new ArrayList<>();
    }

    /**
     * @param player The player whose state should be toggled.
     */
    @Override
    public void toggle(Player player) {
        if(playersAffected.contains(player.getUniqueId()))
            playersAffected.remove(player.getUniqueId());
        else playersAffected.add(player.getUniqueId());
    }

    /**
     * This method is called when a player breaks a block.
     * If a player is affected from the troll the block which
     * they broke will turn into lava.
     *
     * @param event The event that should be listened for.
     */
    @EventHandler
    public void handleBlockBreak(final BlockBreakEvent event) {

        final Player player = event.getPlayer();
        final Block block = event.getBlock();

        // check if the player is affected by the troll.
        if (!playersAffected.contains(player.getUniqueId()))
            return;

        // replace the block and cancel the event so that
        // the player does not get any items from the original block.
        event.setCancelled(true);
        Bukkit.getScheduler().runTaskLater(this.main, () -> {
            player.getWorld().getBlockAt(block.getLocation()).setType(Material.LAVA);
        }, 3L);


    }
}