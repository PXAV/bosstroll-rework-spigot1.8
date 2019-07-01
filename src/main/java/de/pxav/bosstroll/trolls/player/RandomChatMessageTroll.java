package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.UniqueTroll;
import org.bukkit.entity.Player;

import java.util.Collections;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class RandomChatMessageTroll implements UniqueTroll {

    // instance of the main class
    private BossTroll main;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public RandomChatMessageTroll(final BossTroll main) {
        this.main = main;
    }

    @Override
    public void execute(Player player) {
        Collections.shuffle(this.main.getConfigurationFile().getRandomTrollMessages());
        final String message = this.main.getConfigurationFile().getRandomTrollMessages().get(0);
        if(message != null)
            player.chat(message);
    }
}