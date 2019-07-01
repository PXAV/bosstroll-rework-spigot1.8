package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.ToggleTroll;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class FreezeTroll implements ToggleTroll, Listener {

    // instance of the main class
    private BossTroll main;

    private List<UUID> playersAffected;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public FreezeTroll(final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
        this.playersAffected = new ArrayList<>();
    }

    @Override
    public void toggle(Player player) {
        if(playersAffected.contains(player.getUniqueId())) {
            playersAffected.remove(player.getUniqueId());
            player.setWalkSpeed(.2F);
        } else {
            playersAffected.add(player.getUniqueId());
            player.setWalkSpeed(0F);
        }
    }

    @EventHandler
    public void handlePlayerMove(final PlayerMoveEvent event) {

        final Player player = event.getPlayer();

        if (!playersAffected.contains(player.getUniqueId())
                || event.getFrom().getY() == event.getTo().getY())
            return;

        player.teleport(event.getFrom());

    }

    @EventHandler
    public void handlePlayerToggleFlight(final PlayerToggleFlightEvent event) {

        final Player player = event.getPlayer();

        if (playersAffected.contains(player.getUniqueId()))
            event.setCancelled(true);

    }

}