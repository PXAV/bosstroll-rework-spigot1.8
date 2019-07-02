package de.pxav.bosstroll.trolls.templates;

import org.bukkit.entity.Player;

/**
 * The Unique troll is a troll that can only be executed, but not
 * be stretched over a certain time period.
 *
 * @author pxav
 */
public interface UniqueTroll {

    /**
     * Execute the code for the troll event.
     *
     * @param player The player who should be affected by the troll.
     */
    void execute(final Player player);

}
