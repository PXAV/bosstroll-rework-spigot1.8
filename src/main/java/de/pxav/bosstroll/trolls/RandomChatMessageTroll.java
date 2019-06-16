package de.pxav.bosstroll.trolls;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
        Collections.shuffle(this.main.getConfigurationFile().getRandomTrollMessgages());
        player.chat(this.main.getConfigurationFile().getRandomTrollMessgages().get(0));
    }
}