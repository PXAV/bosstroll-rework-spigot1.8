package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.TimedTroll;
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
 * This troll forces the player to solve a math
 * problem to not get killed.
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

    /**
     * This method says which problem he has to solve and
     * that he will get killed within n seconds if he does
     * not solve the exercise.
     * This method also starts the scheduler which will check
     * if the player has do die later.
     *
     * @param player The player who is affected from the troll.
     */
    @Override
    public void begin(Player player) {

        // pick a random task from the config file.
        Collections.shuffle(this.main.getConfigurationFile().getMathExercises());
        final MathExercise mathExercise = this.main.getConfigurationFile().getMathExercises().get(0);
        playersSolving.put(player.getUniqueId(), mathExercise);

        this.main.getMessageUtils().sendMultipleLineMessage(player, new String[] {
                "§7Please solve the following math execise to not get killed!",
                "§7Type your answer into the chat within §c" + this.main.getConfigurationFile().getMathTrollTime() + "§7 seconds.",
                "§c" + mathExercise.getTask()
        }, false);

        // start the scheduler which will call the #end() method.
        Bukkit.getScheduler().runTaskLater(this.main, () -> {
            this.end(player);
        }, 20 * this.main.getConfigurationFile().getMathTrollTime());
    }

    /**
     * This method checks if the player has solved his math
     * problem. If not it will kill them.
     *
     * @param player The player who is affected from the troll.
     */
    @Override
    public void end(Player player) {
        if(playersSolving.containsKey(player.getUniqueId())) {
            player.sendMessage("§7You could not solve the math exercise, so you get killed.");
            player.setHealth(0.0D);
            playersSolving.remove(player.getUniqueId());
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