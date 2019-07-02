package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.UniqueTroll;
import org.bukkit.entity.Player;

/**
 * This troll sends a message to the player that
 * says he is now operator on the server.
 *
 * @author pxav
 */
public class FakeOperatorTroll implements UniqueTroll {

    private BossTroll main;

    public FakeOperatorTroll(BossTroll main) {
        this.main = main;
    }

    @Override
    public void execute(Player player) {
        player.sendMessage("§7§o[Server: Opped " + player.getName() + "]");
    }

}
