package de.pxav.bosstroll.trolls.server;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.GlobalUniqueTroll;
import de.pxav.bosstroll.trolls.templates.UniqueTroll;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * A troll that teleports all players
 * to the location of the initiator.
 *
 * @author pxav
 */
public class TeleportAllTroll implements GlobalUniqueTroll {

    // instance of the main class
    private BossTroll main;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public TeleportAllTroll(final BossTroll main) {
        this.main = main;
    }

    /**
     * Teleports all players on the server to the initiator.
     *
     * @param initiator     The player who triggered the troll.
     * @param affectSelf    Should the initiator be affected from the troll as well?
     */
    @Override
    public void execute(Player initiator, boolean affectSelf) {
        Bukkit.getOnlinePlayers().forEach(current -> {
            if (affectSelf)
                current.teleport(initiator);
            else if (!initiator.getName().equals(current.getName()))
                current.teleport(initiator);
        });
    }
}