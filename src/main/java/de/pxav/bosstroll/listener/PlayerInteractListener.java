package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.items.TrollToolsInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class PlayerInteractListener implements Listener {

    private BossTroll main;

    public PlayerInteractListener(BossTroll main) {
        this.main = main;
    }

    @EventHandler
    public void handlePlayerInteract(PlayerInteractEvent event) {

        if (event.getItem() == null
                || event.getItem().getItemMeta() == null
                || event.getItem().getItemMeta().getDisplayName() == null)
            return;

        Player player = event.getPlayer();
        String displayName = event.getItem().getItemMeta().getDisplayName();
        Material type = event.getItem().getType();

        if (type == Material.IRON_SPADE && displayName.equalsIgnoreCase(TrollToolsInventory.SNOW_CANON_TITLE)) {
            player.playSound(player.getLocation(), Sound.FIRE_IGNITE, 3, 1);
            player.launchProjectile(Snowball.class);
            return;
        }

    }

}
