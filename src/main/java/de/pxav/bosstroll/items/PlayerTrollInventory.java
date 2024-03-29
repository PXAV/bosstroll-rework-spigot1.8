package de.pxav.bosstroll.items;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This class is used to build the inventory to troll
 * certain players.
 *
 * @author pxav
 */
public class PlayerTrollInventory {

    // instance of the main class
    private BossTroll main;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public PlayerTrollInventory(final BossTroll main) {
        this.main = main;
    }

    /**
     * Opens the inventory and automatically registers the needed item listeners.
     * This method depends on the data from {@link de.pxav.bosstroll.utils.PlayerInfo}, so please
     * insert the troll data before executing this method.
     *
     * @param player The player who should see the inventory.
     */
    public void open(final Player player) {

        // fetch the target from the PlayerInfo
        final Player target = Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()));
        final Inventory inventory = Bukkit.createInventory(null, 54, "§cTROLL " + target.getName());

        // set fill material into the inventory.
        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, new ItemBuilder().buildFillMaterial());

        inventory.setItem(4, new SkullBuilder(3)
                .setDisplayName("§c" + target.getName())
                .setLore(new String[] {
                        "§7is the player you are currently trolling.",
                })
                .setSkullOwner(target.getName())
                .build());

        inventory.setItem(10, new ItemBuilder(Material.FLINT_AND_STEEL)
                .setDisplayName("§cRing of fire")
                .setLore(new String[] {
                        "§7Spawns fire blocks around the",
                        "§7player in a range of 3 blocks."
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getFireRingTroll().execute(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(11, new ItemBuilder(Material.DIRT)
                .setDisplayName("§cDrop items")
                .setLore(new String[] {
                        "§7Drops all items of the players'",
                        "§7inventory onto the ground."
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getDropInventoryTroll().execute(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(12, new ItemBuilder(Material.PAPER)
                .setDisplayName("§cSolve Math problem")
                .setLore(new String[] {
                        "§7Picks a random math problem to solve.",
                        "§7If the player does not solve it within",
                        "§7a certain time, they will get killed."
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getMathTroll().begin(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(13, new ItemBuilder(Material.VINE)
                .setDisplayName("§cLet the player lag around")
                .setLore(new String[] {
                        "§7Executes all events the player does with",
                        "§7a certain delay to make him think that his",
                        "§7internet connection is very slow.",
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getLagPlayerConnectionTroll().toggle(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(14, new ItemBuilder(Material.COAL, 1)
                .setDisplayName("§cSend random chat message")
                .setLore(new String[] {
                        "§7Picks a random chat message from a list and",
                        "§7broadcasts a message under the name of ",
                        "§7your victim.",
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getRandomChatMessageTroll().execute(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(15, new ItemBuilder(Material.LAVA_BUCKET)
                .setDisplayName("§cLava Blocks")
                .setLore(new String[] {
                        "§7When a player breaks a block a lava",
                        "§7block will appear at that location.",
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getLavaBlockTroll().toggle(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(16, new ItemBuilder(Material.WATER_BUCKET)
                .setDisplayName("§cWater Blocks")
                .setLore(new String[] {
                        "§7When a player breaks a block a water",
                        "§7block will appear at that location.",
                })
                .addListener(player, inventory.getName(), event -> {
                    System.out.println(Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId())));
                    event.setCancelled(true);
                    this.main.getWaterBlockTroll().toggle(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(19, new ItemBuilder(Material.DIAMOND_SWORD)
                .setDisplayName("§cItem Remover")
                .setLore(new String[] {
                        "§7When a player interacts with an item",
                        "§7(for example his sword during a fight)",
                        "§7this item will get cleared from his inventory."
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getItemRemoveTroll().toggle(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(20, new ItemBuilder(Material.ICE)
                .setDisplayName("§cFreeze Player")
                .setLore(new String[] {
                        "§7A player can't move anymore - ",
                        "§7no matter if walking, flying or jumping."
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getFreezeTroll().toggle(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(21, new ItemBuilder(Material.GRASS)
                .setDisplayName("§cShow demo screen")
                .setLore(new String[] {
                        "§7A window will pop up which says that the",
                        "§7player has only some time left until they",
                        "§7need to buy minecraft."
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    Bukkit.getScheduler().runTaskLater(this.main, () -> {
                        this.main.getDemoScreenTroll().execute(
                                Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                        );
                    }, 2L);
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(22, new ItemBuilder(Material.NETHER_STAR)
                .setDisplayName("§cFake AntiCheat flag")
                .setLore(new String[] {
                        "§7The player will glide and lag around",
                        "§7as if he is flagged by the AntiCheat system"
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getFakeCheatTroll().toggle(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(23, new ItemBuilder(Material.WOOD_AXE)
                .setDisplayName("§cFake OP")
                .setLore(new String[] {
                        "§7The player will get a message that",
                        "§7says they are now operator."
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getFakeOperatorTroll().execute(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(24, new ItemBuilder(Material.BANNER, 1)
                .setDisplayName("§cFake Ban")
                .setLore(new String[] {
                        "§7The player will get a message that",
                        "§7says they are now banned from the server."
                })
                .addListener(player, inventory.getName(), event -> {
                    event.setCancelled(true);
                    this.main.getFakeBanTroll().execute(
                            Bukkit.getPlayer(this.main.getPlayerInfo().getPlayersTrolling().get(player.getUniqueId()))
                    );
                    player.closeInventory();
                })
                .build()
        );

        // finally open the inventory to the player
        player.openInventory(inventory);

    }

}