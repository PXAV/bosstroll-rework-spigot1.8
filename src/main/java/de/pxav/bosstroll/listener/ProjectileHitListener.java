package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.items.TrollToolsInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

/**
 * This class handles the case when any projectile (Arrow, FireBall, etc.)
 * hits the ground or a player.
 *
 * @author pxav
 */
public class ProjectileHitListener implements Listener {

    private BossTroll main;

    public ProjectileHitListener(BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
    }

    /**
     * This method is called when a projectile hits the ground or a player.
     *
     * @param event The event object, which passes data
     *              like which projectile has just landed.
     */
    @EventHandler
    public void handleProjectileHit(ProjectileHitEvent event) {

        Projectile projectile = event.getEntity();

        // check if a player has shot this projectile
        if (projectile.getShooter() == null
                || !(projectile.getShooter() instanceof Player))
            return;

        Player player = (Player) projectile.getShooter();
        Location landingLocation = projectile.getLocation();
        World world = landingLocation.getWorld();

        // if a fire ball lands on the ground, create an explosion
        if (projectile.getType() == EntityType.FIREBALL) {
            landingLocation.getWorld().createExplosion(landingLocation, this.main.getConfigurationFile().getFireBallExplosionRange(), true);
            return;
        }

        if (projectile.getType() != EntityType.ARROW
                || player.getItemInHand() == null
                || player.getItemInHand().getItemMeta().getDisplayName() == null)
            return;

        if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(TrollToolsInventory.CREEPER_BOW_TITLE)) {
            world.spawn(landingLocation, Creeper.class);
            player.playSound(player.getLocation(), Sound.CREEPER_HISS, 3, 1);
            return;
        }

        if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(TrollToolsInventory.EXPLOSION_BOW_TITLE)) {
            world.createExplosion(landingLocation, this.main.getConfigurationFile().getExplosionBowRange(), true);
            return;
        }

        if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(TrollToolsInventory.FIRE_BOW_TITLE)) {
            world.getBlockAt(landingLocation).setType(Material.FIRE);
            player.playSound(player.getLocation(), Sound.FIRE_IGNITE, 3, 1);
            player.playSound(player.getLocation(), Sound.FIRE, 3, 1);
            return;
        }

        if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(TrollToolsInventory.WEB_BOW_TITLE)) {
            world.getBlockAt(landingLocation).setType(Material.WEB);
            player.playSound(player.getLocation(), Sound.SPIDER_IDLE, 3, 1);
            return;
        }

        if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(TrollToolsInventory.LAVA_BOW_TITLE)) {
            world.getBlockAt(landingLocation).setType(Material.LAVA);
            player.playSound(player.getLocation(), Sound.LAVA_POP, 3, 1);
            return;
        }

        if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(TrollToolsInventory.LIGHTNING_BOW_TITLE)) {
            world.strikeLightning(landingLocation);
        }

    }

}
