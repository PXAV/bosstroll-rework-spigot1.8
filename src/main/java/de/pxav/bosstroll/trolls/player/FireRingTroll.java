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
 * A class description goes here.
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