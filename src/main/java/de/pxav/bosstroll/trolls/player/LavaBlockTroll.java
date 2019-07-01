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

    private List<UUID> playersAffected = new ArrayList<>();

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public LavaBlockTroll(final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
    }

    @Override
    public void toggle(Player player) {
        if(playersAffected.contains(player.getUniqueId()))
            playersAffected.remove(player.getUniqueId());
        else playersAffected.add(player.getUniqueId());
    }

    @EventHandler
    public void handleBlockBreak(final BlockBreakEvent event) {

        final Player player = event.getPlayer();
        final Block block = event.getBlock();

        if (!playersAffected.contains(player.getUniqueId()))
            return;

        event.setCancelled(true);
        Bukkit.getScheduler().runTaskLater(this.main, () -> {
            player.getWorld().getBlockAt(block.getLocation()).setType(Material.LAVA);
        }, 3L);


    }
}