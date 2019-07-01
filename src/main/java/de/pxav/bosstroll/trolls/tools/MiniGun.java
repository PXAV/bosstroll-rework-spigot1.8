package de.pxav.bosstroll.trolls.tools;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class MiniGun {

    private BossTroll main;
    private List<UUID> users;

    public MiniGun(BossTroll main) {
        this.main = main;
        this.users = new ArrayList<>();
    }

    public void enableScheduler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.main, () -> {
            this.users.forEach(current -> {
                Bukkit.getPlayer(current).launchProjectile(Arrow.class);
            });
        }, 20L, 3L);
    }

    public void toggle(Player player) {
        if (this.users.contains(player.getUniqueId()))
            this.interrupt(player);
        else this.users.add(player.getUniqueId());
    }

    public void interrupt(Player player) {
        this.users.remove(player.getUniqueId());
    }

}
