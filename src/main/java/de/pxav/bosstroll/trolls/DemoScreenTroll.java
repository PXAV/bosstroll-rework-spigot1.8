package de.pxav.bosstroll.trolls;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.entity.Player;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class DemoScreenTroll implements UniqueTroll {

    // instance of the main class
    private BossTroll main;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public DemoScreenTroll(final BossTroll main) {
        this.main = main;
    }

    @Override
    public void execute(Player player) {
    }

}