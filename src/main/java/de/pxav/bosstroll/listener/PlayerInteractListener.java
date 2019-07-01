package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.items.TrollToolsInventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
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

        if (type == Material.IRON_SPADE
                && displayName.equalsIgnoreCase(TrollToolsInventory.SNOW_CANON_TITLE)
                && this.isRightClickAction(event.getAction())) {
            player.playSound(player.getLocation(), Sound.FIRE_IGNITE, 3, 1);
            player.launchProjectile(Snowball.class);
            return;
        }

        if (type == Material.HOPPER
                && displayName.equalsIgnoreCase(TrollToolsInventory.MINI_GUN_TITLE)
                && this.isRightClickAction(event.getAction())) {
            this.main.getMiniGun().toggle(player);
            return;
        }

        if (type == Material.STICK
                && displayName.equalsIgnoreCase(TrollToolsInventory.FIREBALL_TITLE)
                && this.isRightClickAction(event.getAction())) {
            player.launchProjectile(Fireball.class);
        }

    }

    private boolean isRightClickAction(Action action) {
        return action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK;
    }

}
