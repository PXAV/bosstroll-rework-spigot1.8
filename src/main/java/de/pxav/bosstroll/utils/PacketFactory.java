package de.pxav.bosstroll.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class PacketFactory {

    public PacketPlayOutChat createActionbar(String message, byte data) {
        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}");
        return new PacketPlayOutChat(iChatBaseComponent, data);
    }

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

    public void sendTitlePacket(Player player, String title, String subTitle) {
        this.sendTitlePacket(player, title, subTitle, 20, 60, 20);
    }

}
