package de.pxav.bosstroll.trolls;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

    private List<UUID> playersAffected = new ArrayList<>();

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public FreezeTroll(final BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
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

}