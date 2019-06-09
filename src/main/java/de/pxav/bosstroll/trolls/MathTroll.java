package de.pxav.bosstroll.trolls;

import com.avaje.ebean.validation.Email;
import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.utils.MathExercise;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class MathTroll implements TimedTroll, Listener {

    private BossTroll main;
    private Map<UUID, MathExercise> playersSolving;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public MathTroll(final BossTroll main) {
        this.main = main;
        this.playersSolving = new HashMap<>();
        this.main.getServer().getPluginManager().registerEvents(this, this.main);
    }

    public void begin(Player player) {
        Collections.shuffle(this.main.getConfigurationFile().getMathExercises());
        final MathExercise mathExercise = this.main.getConfigurationFile().getMathExercises().get(0);
        playersSolving.put(player.getUniqueId(), mathExercise);

        this.main.getMessageUtils().sendMultipleLineMessage(player, new String[] {
                "§7Please solve the following math execise to not get killed!",
                "§7Type your answer into the chat within §c" + this.main.getConfigurationFile().getMathTrollTime() + "§7 seconds.",
                "§c" + mathExercise.getTask()
        }, false);

        Bukkit.getScheduler().runTaskLater(this.main, () -> {
            this.end(player);
        }, 20 * this.main.getConfigurationFile().getMathTrollTime());
    }

    public void end(Player player) {
        if(playersSolving.containsKey(player.getUniqueId())) {
            player.sendMessage("§7You could not solve the math exercise, so you get killed.");
            player.setHealth(0.0D);
        }
    }

    @EventHandler
    public void handleAsyncChat(final AsyncPlayerChatEvent event) {

        if(!playersSolving.containsKey(event.getPlayer().getUniqueId()))
            return;

        final Player player = event.getPlayer();
        final String message = event.getMessage().replace("%", "%%");

        try {
            final int inputResult = Integer.parseInt(message);
            if(inputResult == playersSolving.get(player.getUniqueId()).getSolution()) {
                player.sendMessage(this.main.getPrefix() + "§7Your solution was §aright§7. You stay alive.");
                playersSolving.remove(player.getUniqueId());
            }
        } catch (NumberFormatException e) {
            player.sendMessage(this.main.getPrefix() + "§7Your result was §cwrong§7. So you will be killed!");
            player.setHealth(0.0D);
        }

    }
}