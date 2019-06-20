package de.pxav.bosstroll.trolls;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class FakeCheatTroll implements ToggleTroll, Listener {

    // instance of the main class
    private BossTroll main;

    private List<UUID> playersAffected = new ArrayList<>();

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public FakeCheatTroll(final BossTroll main) {
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
        }
    }

    @EventHandler
    public void handlePlayerMove(final PlayerMoveEvent event) {

        final Player player = event.getPlayer();

        if(!playersAffected.contains(player.getUniqueId())) {
            return;
        }

        player.setAllowFlight(true);
        player.setVelocity(player.getLocation().getDirection().setZ(.1).setX(.1));
        player.setVelocity(player.getLocation().getDirection().setZ(-.1).setX(-.1));
        player.setVelocity(player.getLocation().getDirection().setY(-9));
        player.setAllowFlight(false);
        player.setWalkSpeed(.000001F);

    }

}