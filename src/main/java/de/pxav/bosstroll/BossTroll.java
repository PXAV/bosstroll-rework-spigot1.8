package de.pxav.bosstroll;

import de.pxav.bosstroll.commands.TrollCommand;
import de.pxav.bosstroll.configuration.ConfigurationFile;
import de.pxav.bosstroll.items.PlayerTrollInventory;
import de.pxav.bosstroll.items.ServerTrollInventory;
import de.pxav.bosstroll.items.TrollToolsInventory;
import de.pxav.bosstroll.listener.InventoryClickListener;
import de.pxav.bosstroll.listener.InventoryCloseListener;
import de.pxav.bosstroll.listener.PlayerInteractListener;
import de.pxav.bosstroll.listener.PlayerQuitListener;
import de.pxav.bosstroll.trolls.player.*;
import de.pxav.bosstroll.trolls.server.HackMessageTroll;
import de.pxav.bosstroll.trolls.server.TeleportAllTroll;
import de.pxav.bosstroll.utils.ListenerUtil;
import de.pxav.bosstroll.utils.MessageUtils;
import de.pxav.bosstroll.utils.PacketFactory;
import de.pxav.bosstroll.utils.PlayerInfo;
import lombok.Getter;
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
    private ServerTrollInventory serverTrollInventory;
    private PacketFactory packetFactory;
    private TrollToolsInventory trollToolsInventory;

    // player trolls
    private MathTroll mathTroll;
    private DropInventoryTroll dropInventoryTroll;
    private FireRingTroll fireRingTroll;
    private LagPlayerConnectionTroll lagPlayerConnectionTroll;
    private RandomChatMessageTroll randomChatMessageTroll;
    private LavaBlockTroll lavaBlockTroll;
    private WaterBlockTroll waterBlockTroll;
    private ItemRemoveTroll itemRemoveTroll;
    private FreezeTroll freezeTroll;
    private DemoScreenTroll demoScreenTroll;
    private FakeCheatTroll fakeCheatTroll;
    private FakeOperatorTroll fakeOperatorTroll;
    private FakeBanTroll fakeBanTroll;

    // server trolls
    private TeleportAllTroll teleportAllTroll;
    private HackMessageTroll hackMessageTroll;

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
        this.serverTrollInventory = new ServerTrollInventory(this);
        this.serverTrollInventory.prepare();
        this.trollToolsInventory = new TrollToolsInventory(this);
        this.packetFactory = new PacketFactory();

        new TrollCommand("troll", this);

        new InventoryClickListener(this);
        new InventoryCloseListener(this);
        new PlayerQuitListener(this);
        new PlayerInteractListener(this);

        this.registerTrolls();

    }

    private void registerTrolls() {
        this.mathTroll = new MathTroll(this);
        this.freezeTroll = new FreezeTroll(this);
        this.fakeBanTroll = new FakeBanTroll(this);
        this.fireRingTroll = new FireRingTroll(this);
        this.lavaBlockTroll = new LavaBlockTroll(this);
        this.fakeCheatTroll = new FakeCheatTroll(this);
        this.waterBlockTroll = new WaterBlockTroll(this);
        this.itemRemoveTroll = new ItemRemoveTroll(this);
        this.demoScreenTroll = new DemoScreenTroll(this);
        this.fakeOperatorTroll = new FakeOperatorTroll(this);
        this.dropInventoryTroll = new DropInventoryTroll(this);
        this.randomChatMessageTroll = new RandomChatMessageTroll(this);
        this.lagPlayerConnectionTroll = new LagPlayerConnectionTroll(this);

        this.teleportAllTroll = new TeleportAllTroll(this);
        this.hackMessageTroll = new HackMessageTroll(this);
    }

    @Override
    public void onDisable() {

    }
}
