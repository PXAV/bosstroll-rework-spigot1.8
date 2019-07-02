package de.pxav.bosstroll.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * This class contains useful methods to manage packets
 * for titles and action bars.
 *
 * @author pxav
 */
public class PacketFactory {

    /**
     * Prepares an actionbar packet.
     * An action bar is a message which is displayed
     * above the player's hot bar.
     *
     * @param message The message which should be displayed in the action bar.
     * @return The final action bar packet.
     */
    public PacketPlayOutChat createActionbar(String message) {
        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}");
        return new PacketPlayOutChat(iChatBaseComponent, (byte) 2);
    }

    /**
     * This method sends a title to a certain player.
     * A title usually covers the entire screen.
     *
     * @param player    The player who should see the title
     * @param title     The upper and bigger title.
     * @param subTitle  The lower and smaller title.
     * @param fadeIn    How many ticks should it take to fade the title in?
     * @param fadeOut   How many ticks should it take to fade the title out?
     * @param stay      How many ticks should the title stay with a 100% opacity?
     */
    public void sendTitlePacket(Player player, String title, String subTitle, int fadeIn, int fadeOut, int stay) {
        IChatBaseComponent titleComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title + "\"}");
        IChatBaseComponent subTitleComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + subTitle + "\"}");
        PacketPlayOutTitle timePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, titleComponent, fadeIn, stay, fadeOut);

        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleComponent);
        PacketPlayOutTitle subTitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subTitleComponent);

        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(timePacket);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(subTitlePacket);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(titlePacket);

    }

    /**
     * This method executes {@code #sendTitlePacket(player, title, subTitle, fadeIn, fadeOut, stay)}, but
     * inserts default values for the appearance time.
     *
     * @param player    The player who should see the title
     * @param title     The upper and bigger title.
     * @param subTitle  The lower and smaller title.
     */
    public void sendTitlePacket(Player player, String title, String subTitle) {
        this.sendTitlePacket(player, title, subTitle, 20, 60, 20);
    }

}
