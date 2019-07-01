package de.pxav.bosstroll.items;

import de.pxav.bosstroll.BossTroll;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class TrollToolsInventory {

    // instance of the main class
    private BossTroll main;

    public static final String FIREBALL_TITLE = "§c§lFIREBALL";
    public static final String WEB_BOW_TITLE = "§c§lWEB BOW";
    public static final String LIGHTNING_BOW_TITLE = "§c§lLIGHTNING BOW";
    public static final String LAVA_BOW_TITLE = "§c§lLAVA BOW";
    public static final String EXPLOSION_BOW_TITLE = "§c§lEXPLOSION BOW";
    public static final String CREEPER_BOW_TITLE = "§c§lCREEPER BOW";
    public static final String FIRE_BOW_TITLE = "§c§lFIRE BOW";
    public static final String SNOW_CANON_TITLE = "§c§lSNOW CANON";
    public static final String MINI_GUN_TITLE = "§c§lMINIGUN";

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public TrollToolsInventory(final BossTroll main) {
        this.main = main;
    }

    public void open(final Player player) {

        final String title = "§cTroll Tools";
        final Inventory inventory = Bukkit.createInventory(null, 36, title);

        for (int i = 0; i < inventory.getSize(); i++)
            inventory.setItem(i, new ItemBuilder().buildFillMaterial());

        inventory.setItem(10, new ItemBuilder(Material.FIREBALL)
                .setDisplayName("§cFireball")
                .setLore(new String[]{
                        "§7All players who are currently online",
                        "§7will get teleported to your location",
                        "§7immediately"
                })
                .addListener(player, title, event -> {
                    event.setCancelled(true);
                    player.getInventory().addItem(new ItemBuilder(Material.STICK)
                            .setDisplayName(FIREBALL_TITLE)
                            .build()
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(11, new ItemBuilder(Material.BOW)
                .setDisplayName("§cExplosion Bow")
                .setLore(new String[]{
                        "§7When an arrow of this bow lands",
                        "§7on the ground an explosion will",
                        "§7occur at the arrow's location."
                })
                .addListener(player, title, event -> {
                    event.setCancelled(true);
                    player.getInventory().addItem(new ItemBuilder(Material.BOW)
                            .setDisplayName(EXPLOSION_BOW_TITLE)
                            .build()
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(12, new ItemBuilder(Material.BOW)
                .setDisplayName("§cCreeper Bow")
                .setLore(new String[]{
                        "§7When an arrow of this bow lands",
                        "§7on the ground a creeper will be",
                        "§7spawned at the arrow's location"
                })
                .addListener(player, title, event -> {
                    event.setCancelled(true);
                    player.getInventory().addItem(new ItemBuilder(Material.BOW)
                            .setDisplayName(CREEPER_BOW_TITLE)
                            .build()
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(13, new ItemBuilder(Material.BOW)
                .setDisplayName("§cLava Bow")
                .setLore(new String[]{
                        "§7When an arrow of this bow lands",
                        "§7on the ground a lava block will be",
                        "§7placed at the arrow's location"
                })
                .addListener(player, title, event -> {
                    event.setCancelled(true);
                    player.getInventory().addItem(new ItemBuilder(Material.BOW)
                            .setDisplayName(LAVA_BOW_TITLE)
                            .build()
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(14, new ItemBuilder(Material.BOW)
                .setDisplayName("§cCobweb Bow")
                .setLore(new String[]{
                        "§7When an arrow of this bow lands",
                        "§7on the ground a cobweb will be",
                        "§7placed at the arrow's location"
                })
                .addListener(player, title, event -> {
                    event.setCancelled(true);
                    player.getInventory().addItem(new ItemBuilder(Material.BOW)
                            .setDisplayName(WEB_BOW_TITLE)
                            .build()
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(15, new ItemBuilder(Material.BOW)
                .setDisplayName("§cLightning Bow")
                .setLore(new String[]{
                        "§7When an arrow of this bow lands",
                        "§7on the ground a lightning will be",
                        "§7spawned at the arrow's location"
                })
                .addListener(player, title, event -> {
                    event.setCancelled(true);
                    player.getInventory().addItem(new ItemBuilder(Material.BOW)
                            .setDisplayName(LIGHTNING_BOW_TITLE)
                            .build()
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(16, new ItemBuilder(Material.BOW)
                .setDisplayName("§cFire Bow")
                .setLore(new String[]{
                        "§7When an arrow of this bow lands",
                        "§7on the ground a fire block will be",
                        "§7spawned at the arrow's location"
                })
                .addListener(player, title, event -> {
                    event.setCancelled(true);
                    player.getInventory().addItem(new ItemBuilder(Material.BOW)
                            .setDisplayName(FIRE_BOW_TITLE)
                            .build()
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(19, new ItemBuilder(Material.IRON_SPADE)
                .setDisplayName("§cSnowball canon")
                .setLore(new String[]{
                        "§7A canon that shoots snowballs"
                })
                .addListener(player, title, event -> {
                    event.setCancelled(true);
                    player.getInventory().addItem(new ItemBuilder(Material.IRON_SPADE)
                            .setDisplayName(SNOW_CANON_TITLE)
                            .build()
                    );
                    player.closeInventory();
                })
                .build()
        );

        inventory.setItem(20, new ItemBuilder(Material.HOPPER)
                .setDisplayName("§cMinigun")
                .setLore(new String[]{
                        "§7A canon that shoots a lot of arrows",
                        "§7in a short period of time."
                })
                .addListener(player, title, event -> {
                    event.setCancelled(true);
                    player.getInventory().addItem(new ItemBuilder(Material.HOPPER)
                            .setDisplayName(MINI_GUN_TITLE)
                            .build()
                    );
                    player.closeInventory();
                })
                .build()
        );

        player.openInventory(inventory);

    }

}
