package de.pxav.bosstroll.trolls.player;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.trolls.templates.UniqueTroll;
import net.minecraft.server.v1_8_R3.PacketPlayOutGameStateChange;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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
        final CraftPlayer craftPlayer = (CraftPlayer) player;
        final PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(7, 100);
        craftPlayer.getHandle().playerConnection.sendPacket(packet);
    }

}