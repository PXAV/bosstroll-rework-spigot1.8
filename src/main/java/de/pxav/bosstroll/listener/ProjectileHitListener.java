package de.pxav.bosstroll.listener;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class ProjectileHitListener implements Listener {

    private BossTroll main;

    public ProjectileHitListener(BossTroll main) {
        this.main = main;
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
    }

    @EventHandler
    public void handleProjectileHit(ProjectileHitEvent event) {

        Projectile projectile = event.getEntity();

        if (projectile.getShooter() == null
                || !(projectile.getShooter() instanceof Player))
            return;

        Player player = (Player) projectile.getShooter();
        Location landingLocation = projectile.getLocation();

        if (projectile.getType() == EntityType.FIREBALL) {
            landingLocation.getWorld().createExplosion(landingLocation, this.main.getConfigurationFile().getFireBallExplosionRange(), true);
            return;
        }


    }

}
