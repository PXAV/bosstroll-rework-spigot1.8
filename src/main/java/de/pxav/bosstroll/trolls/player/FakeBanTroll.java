package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.UniqueTroll;
import org.bukkit.entity.Player;

/**
 * This troll kicks the player with a ban message so
 * that they think they are banned from the server now.
 *
 * @author pxav
 */
public class FakeBanTroll implements UniqueTroll {

    private BossTroll main;

    public FakeBanTroll(BossTroll main) {
        this.main = main;
    }

    /**
     * Kicks the player from the server with the message from the
     * configuration file
     *
     * @param player The player who should be kicked.
     */
    @Override
    public void execute(Player player) {
        player.kickPlayer(this.main.getConfigurationFile().getFakeBanMessage());
    }
}
