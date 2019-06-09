package de.pxav.bosstroll;

import de.pxav.bosstroll.commands.TrollCommand;
import de.pxav.bosstroll.configuration.ConfigurationFile;
import de.pxav.bosstroll.trolls.DropInventoryTroll;
import de.pxav.bosstroll.trolls.FireRingTroll;
import de.pxav.bosstroll.trolls.MathTroll;
import de.pxav.bosstroll.utils.MessageUtils;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the main class of the plugin.
 *
 * @author pxav
 */

@Getter
public class BossTroll extends JavaPlugin {

    private String prefix = "§8[§cTroll§8] §r";
    private String defaultFilePath = "plugins//BossTroll";

    private ConfigurationFile configurationFile;
    private MessageUtils messageUtils;

    private MathTroll mathTroll;
    private DropInventoryTroll dropInventoryTroll;
    private FireRingTroll fireRingTroll;

    @Override
    public void onEnable() {

        // creates and loads the plugin configuration file
        this.saveDefaultConfig();
        this.configurationFile = new ConfigurationFile(this);
        this.configurationFile.loadFile();
        this.configurationFile.loadSettings();

        this.messageUtils = new MessageUtils(this);

        new TrollCommand("troll", this);

        this.registerTrolls();

        Bukkit.getScheduler().runTaskLater(this, () -> {
            final Player player = Bukkit.getPlayer("OrigPXAV");
            this.fireRingTroll.execute(player);
        }, 200);

    }

    public void registerTrolls() {
        this.mathTroll = new MathTroll(this);
        this.fireRingTroll = new FireRingTroll(this);
        this.dropInventoryTroll = new DropInventoryTroll(this);
    }

    @Override
    public void onDisable() {

    }
}
