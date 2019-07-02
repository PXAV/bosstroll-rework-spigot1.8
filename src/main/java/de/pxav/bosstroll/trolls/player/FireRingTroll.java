package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.UniqueTroll;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This troll spawns a ring of fire blocks around the player.
 *
 * @author pxav
 */
public class FireRingTroll implements UniqueTroll {

    // instance of the main class
    private BossTroll main;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public FireRingTroll(final BossTroll main) {
        this.main = main;
    }

    /**
     * Spawns a ring of fire in a range
     * of 3 blocks around the given player.
     * @param player The player, whose location
     *               should be the center of the ring.
     */
    @Override
    public void execute(Player player) {

        final List<Location> fireLocations = new ArrayList<>();
        fireLocations.add(player.getLocation().add(3, 0, 0));
        fireLocations.add(player.getLocation().add(3, 0, 1));
        fireLocations.add(player.getLocation().add(3, 0, -1));

        fireLocations.add(player.getLocation().add(0, 0, 3));
        fireLocations.add(player.getLocation().add(1, 0, 3));
        fireLocations.add(player.getLocation().add(-1, 0, 3));

        fireLocations.add(player.getLocation().add(-3, 0, 0));
        fireLocations.add(player.getLocation().add(-3, 0, 1));
        fireLocations.add(player.getLocation().add(-3, 0, -1));

        fireLocations.add(player.getLocation().add(0, 0, -3));
        fireLocations.add(player.getLocation().add(1, 0, -3));
        fireLocations.add(player.getLocation().add(-1, 0, -3));

        fireLocations.forEach(current
                -> player.getWorld().getBlockAt(current).setType(Material.FIRE));
        player.playSound(player.getLocation(), Sound.FIRE_IGNITE, 3F, 1F);

    }
}