package de.pxav.bosstroll.trolls.templates;

import org.bukkit.entity.Player;

/**
 * This interface is the extended version of {@code UniqueTroll}.
 * It represents a troll that does not have a certain period of time.
 *
 * @author pxav
 */
public interface GlobalUniqueTroll {

    /**
     * Executes the troll.
     *
     * @param initiator     The player who triggered the troll.
     * @param affectSelf    Should the initiator be affected from the troll as well?
     */
    void execute(final Player initiator, final boolean affectSelf);

}
