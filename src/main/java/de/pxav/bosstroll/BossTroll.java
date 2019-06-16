package de.pxav.bosstroll;

import de.pxav.bosstroll.commands.TrollCommand;
import de.pxav.bosstroll.configuration.ConfigurationFile;
import de.pxav.bosstroll.items.PlayerTrollInventory;
import de.pxav.bosstroll.listener.InventoryClickListener;
import de.pxav.bosstroll.listener.InventoryCloseListener;
import de.pxav.bosstroll.listener.PlayerQuitListener;
import de.pxav.bosstroll.trolls.*;
import de.pxav.bosstroll.utils.ListenerUtil;
import de.pxav.bosstroll.utils.MessageUtils;
import de.pxav.bosstroll.utils.PlayerInfo;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
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
    private PlayerInfo playerInfo;
    private PlayerTrollInventory playerTrollInventory;
    private ListenerUtil listenerUtil;

    private MathTroll mathTroll;
    private DropInventoryTroll dropInventoryTroll;
    private FireRingTroll fireRingTroll;
    private LagPlayerConnectionTroll lagPlayerConnectionTroll;
    private RandomChatMessageTroll randomChatMessageTroll;
    private LavaBlockTroll lavaBlockTroll;

    @Override
    public void onEnable() {

        // creates and loads the plugin configuration file
        this.saveDefaultConfig();
        this.configurationFile = new ConfigurationFile(this);
        this.configurationFile.loadFile();
        this.configurationFile.loadSettings();

        this.listenerUtil = new ListenerUtil(this);
        this.playerInfo = new PlayerInfo(this);
        this.messageUtils = new MessageUtils(this);
        this.playerTrollInventory = new PlayerTrollInventory(this);
        this.playerTrollInventory.prepare();

        new TrollCommand("troll", this);

        new InventoryClickListener(this);
        new InventoryCloseListener(this);
        new PlayerQuitListener(this);

        this.registerTrolls();

    }

    private void registerTrolls() {
        this.mathTroll = new MathTroll(this);
        this.fireRingTroll = new FireRingTroll(this);
        this.lavaBlockTroll = new LavaBlockTroll(this);
        this.dropInventoryTroll = new DropInventoryTroll(this);
        this.randomChatMessageTroll = new RandomChatMessageTroll(this);
        this.lagPlayerConnectionTroll = new LagPlayerConnectionTroll(this);
    }

    @Override
    public void onDisable() {

    }
}
