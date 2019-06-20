package de.pxav.bosstroll.trolls.templates;

import org.bukkit.entity.Player;

/**
 * This class represents a troll event.
 * A TimedTroll is a troll event which takes a certain time until
 * it automatically expires. So a player does not have to stop it
 * manually, but the server does.
 *
 * @author pxav
 */
public interface TimedTroll {

    void begin(final Player player);

    void end(final Player player);

}
