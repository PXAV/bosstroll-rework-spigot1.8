package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.UniqueTroll;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * This troll tells the player that he needs to buy minecraft to be able
 * to play.
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

    /**
     * This method basically sends the player three packets.
     * The main packet shows a window that says how much time the player
     * has left until he has to buy minecraft. The other packets
     * send control help like how the player can move around, etc.
     *
     * @param player The player who should receive the packets.
     */
    @Override
    public void execute(Player player) {
        final CraftPlayer craftPlayer = (CraftPlayer) player;
        final PacketPlayOutGameStateChange welcomePacket = new PacketPlayOutGameStateChange(5, 0.0F);
        final PacketPlayOutGameStateChange moveHelpPacket = new PacketPlayOutGameStateChange(5, 101F);
        final PacketPlayOutGameStateChange jumpHelpPacket = new PacketPlayOutGameStateChange(5, 102F);
        final PacketPlayOutGameStateChange inventoryControl = new PacketPlayOutGameStateChange(5, 103F);
        craftPlayer.getHandle().playerConnection.sendPacket(inventoryControl);
        craftPlayer.getHandle().playerConnection.sendPacket(jumpHelpPacket);
        craftPlayer.getHandle().playerConnection.sendPacket(welcomePacket);
        craftPlayer.getHandle().playerConnection.sendPacket(moveHelpPacket);
    }

}