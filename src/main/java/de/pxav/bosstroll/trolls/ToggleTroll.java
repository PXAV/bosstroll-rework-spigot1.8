package de.pxav.bosstroll.trolls;

import org.bukkit.entity.Player;

/**
 * This class represents a troll event.
 * A ToggleTroll can be in a certain state: On or off.
 * So a player can set the state for a certain player to on
 * and his target will be affected from the troll.
 *
 * @author pxav
 */
public interface ToggleTroll {

    void toggle(final Player player);

}
