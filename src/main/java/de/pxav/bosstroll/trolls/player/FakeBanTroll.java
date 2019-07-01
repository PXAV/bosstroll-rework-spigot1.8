package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.UniqueTroll;
import org.bukkit.entity.Player;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class FakeBanTroll implements UniqueTroll {

    private BossTroll main;

    public FakeBanTroll(BossTroll main) {
        this.main = main;
    }

    @Override
    public void execute(Player player) {
        player.kickPlayer(this.main.getConfigurationFile().getFakeBanMessage());
    }
}
