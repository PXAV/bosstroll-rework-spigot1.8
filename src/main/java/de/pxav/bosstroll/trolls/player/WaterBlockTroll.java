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
public class WaterBlockTroll implements ToggleTroll, Listener {

    // instance of the main class
    private BossTroll main;

    private List<UUID> playersAffected;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public WaterBlockTroll(final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
        this.playersAffected = new ArrayList<>();
    }

    @Override
    public void toggle(Player player) {
        System.out.println(playersAffected);
        System.out.println(player.getUniqueId());
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
            player.getWorld().getBlockAt(block.getLocation()).setType(Material.WATER);
        }, 3L);

    }

}