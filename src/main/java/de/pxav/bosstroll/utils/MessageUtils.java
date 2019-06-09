package de.pxav.bosstroll.utils;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * This class contains util methods for sending messages in the chat.
 *
 * @author pxav
 */
public class MessageUtils {

    // instance of the main class.
    private BossTroll main;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public MessageUtils(final BossTroll main) {
        this.main = main;
    }

    /**
     * Sends a chat message with the given messages to a command sender.
     * This can be used to avoid redundant code for multiple line messages
     * in the chat.
     *
     * @param commandSender The commandSender who should receive the messages
     * @param messages      The messages you actually want to send.
     * @param sendPrefix    Should a prefix be sent in front of every message?
     */
    public void sendMultipleLineMessage(final CommandSender commandSender, final String[] messages, final boolean sendPrefix) {
        for (final String current : messages) {
            if(sendPrefix)
                commandSender.sendMessage(this.main.getPrefix() + current);
            else commandSender.sendMessage(current);
        }
    }

}