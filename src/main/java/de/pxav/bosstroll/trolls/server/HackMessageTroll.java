package de.pxav.bosstroll.trolls.server;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.GlobalUniqueTroll;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * A message that simulates the situation of
 * a hacker attack.
 *
 * @author pxav
 */
public class HackMessageTroll implements GlobalUniqueTroll {

    private BossTroll main;

    public HackMessageTroll(final BossTroll main) {
        this.main = main;
    }

    /**
     * Broadcasts the hack message to all players on the server.
     *
     * @param initiator     The player who triggered the troll.
     * @param affectSelf    Should the initiator be affected from the troll as well?
     */
    @Override
    public void execute(Player initiator, boolean affectSelf) {
        Bukkit.getOnlinePlayers().forEach(current -> {
            if (affectSelf) {
                this.sendHackMessage(current);
            } else if (!initiator.getName().equals(current.getName()))
                this.sendHackMessage(current);
        });
    }

    /**
     * Sends a message which simulates that the server is hacked.
     *
     * @param player The player who should receive this message.
     */
    private void sendHackMessage(final Player player) {

        player.playSound(player.getLocation(), Sound.ENDERDRAGON_HIT, 3, 1);
        this.main.getPacketFactory().sendTitlePacket(player, "§8< §4§lHACK ENABLED §8>", "§7Stealing player data...", 20, 0, 21);

        Bukkit.getScheduler().runTaskLater(this.main, () -> {
            player.playSound(player.getLocation(), Sound.ENDERMAN_SCREAM, 3, 1);
            this.main.getPacketFactory().sendTitlePacket(player, "§c" + UUID.randomUUID(), "§7Hacking information...", 0, 0, 40);
        }, 20L);

        int maxIndex = 10;
        Bukkit.getScheduler().runTaskLater(this.main, () -> {
            for (int i = 0; i < maxIndex; i++)
                this.hackInformation(player, i);
        }, 40);


        Bukkit.getScheduler().runTaskLater(this.main, () -> {
            player.playSound(player.getLocation(), Sound.ENDERDRAGON_DEATH, 3, 1);
            this.main.getPacketFactory().sendTitlePacket(player, "§4HACK FINISHED!", "§7", 0, 20, 100);
        }, maxIndex * 21);

    }

    /**
     * Sends titles with random UUIDs.
     *
     * @param player            The player who should see
     *                          the titles and hear the sounds.
     * @param delayInSeconds    How many seconds the action should be delayed.
     */
    private void hackInformation(Player player, int delayInSeconds) {
        Bukkit.getScheduler().runTaskLater(this.main, () -> {
            player.playSound(player.getLocation(), Sound.FIRE_IGNITE, 3, 1);
            this.main.getPacketFactory().sendTitlePacket(player, "§c" + UUID.randomUUID(), "§7Hacking information...", 0, 0, 22);
        }, delayInSeconds * 20L);
    }

}
