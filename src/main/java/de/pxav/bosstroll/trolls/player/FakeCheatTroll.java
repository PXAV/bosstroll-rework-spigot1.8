package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.ToggleTroll;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This troll lets the player move around as if he had cheats enabled.
 * Then the player is sent to the ground to simulate that the anti cheat
 * flagged him.
 *
 * @author pxav
 */
public class FakeCheatTroll implements ToggleTroll, Listener {

    // instance of the main class
    private BossTroll main;

    private List<UUID> playersAffected;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public FakeCheatTroll(final BossTroll main) {
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
        }
    }


    /**
     * This method is called when a player moves.
     * @param event The event object, which should pass
     *              things like the player who has moved.
     */
    @EventHandler
    public void handlePlayerMove(final PlayerMoveEvent event) {

        final Player player = event.getPlayer();

        // check if the player is affected by the troll.
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