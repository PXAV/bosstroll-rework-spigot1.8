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
 * This class manages the situation when a player interacts
 * with a block or an item.
 *
 * @author pxav
 */
public class PlayerInteractListener implements Listener {

    private BossTroll main;

    public PlayerInteractListener(BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
    }

    /**
     * This method is called when a player
     * interacts with a block or item.
     * @param event the event object, which passes data
     *              like the clicked item or block.
     */
    @EventHandler
    public void handlePlayerInteract(PlayerInteractEvent event) {

        // check if the player does not interact with air
        // and the clicked item has a display name.
        if (event.getItem() == null
                || event.getItem().getItemMeta() == null
                || event.getItem().getItemMeta().getDisplayName() == null)
            return;

        Player player = event.getPlayer();
        String displayName = event.getItem().getItemMeta().getDisplayName();
        Material type = event.getItem().getType();

        // if a player clicks a snow canon, launch a snow ball.
        if (type == Material.IRON_SPADE
                && displayName.equalsIgnoreCase(TrollToolsInventory.SNOW_CANON_TITLE)
                && this.isRightClickAction(event.getAction())) {
            player.playSound(player.getLocation(), Sound.FIRE_IGNITE, 3, 1);
            player.launchProjectile(Snowball.class);
            return;
        }

        // if a player interacts with a mini-gun, toggle the mini-gun state (enable or disable)
        if (type == Material.HOPPER
                && displayName.equalsIgnoreCase(TrollToolsInventory.MINI_GUN_TITLE)
                && this.isRightClickAction(event.getAction())) {
            this.main.getMiniGun().toggle(player);
            return;
        }

        // if a player interacts with the fire ball stick, then launch a fire ball projectile.
        if (type == Material.STICK
                && displayName.equalsIgnoreCase(TrollToolsInventory.FIREBALL_TITLE)
                && this.isRightClickAction(event.getAction())) {
            player.launchProjectile(Fireball.class);
        }

    }

    /**
     * Checks if the given action is a right click action.
     * @param action The action you want to check.
     * @return Does the player executes a right click?
     */
    private boolean isRightClickAction(Action action) {
        return action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK;
    }

}
