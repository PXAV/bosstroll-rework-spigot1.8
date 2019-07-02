package de.pxav.bosstroll.items;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.objects.ItemClickListener;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * This class can be used to build an {@code ItemStack} easily.
 * You can use this class by creating a new instance of this class
 * and simply add properties by adding {@code #propertyName(propertyParameters)}.
 * When you are done, you can call {@code #build()} to finally convert your
 * data into an {@code ItemStack}
 *
 * @see     ItemStack
 * @author  pxav
 */
public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(final Material type) {
        this.itemStack = new ItemStack(type);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(final Material type, final int subId) {
        this.itemStack = new ItemStack(type, 1, (short) subId);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder() {}

    /**
     * Sets the display name of the item meta.
     *
     * @param   displayName The display name you want to set.
     * @return  Return the object itself so that it can be accessed again to add further properties.
     */
    public ItemBuilder setDisplayName(final String displayName) {
        this.itemMeta.setDisplayName(displayName);
        return this;
    }

    /**
     * Sets the amount of items that should be stacked.
     *
     * @param amount    The actual amount you want to set.
     * @return          Return the object itself so that it can be accessed again to add further properties.
     */
    public ItemBuilder setAmount(final int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    /**
     * Sets the lore (description, which is displayed below
     * the display name).
     *
     * @param lore  The elements of the lore (every array is a new line)
     * @return      Return the object itself so that it can be accessed again to add further properties.
     */
    public ItemBuilder setLore(final String[] lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    /**
     * Adds a plugin internal listener. When a player clicks this item
     * something will happen, which can be defined in the {@code #onClick()} method.
     *
     * @param owner         The player who sees the item in one of their inventories.
     * @param inventoryName The title of the inventory, which holds the item.
     * @param listener      The listener interface including the {@code #onClick()} method
     *                      to define what should happen when a player clicks the item.
     * @return              Return the object itself so that it can be accessed again to add further properties.
     */
    public ItemBuilder addListener(final Player owner, final String inventoryName, final ItemListener listener) {
        final ItemClickListener itemClickListener = ItemClickListener
                .createNew()
                .setItemListener(listener)
                .setDisplayName(this.itemMeta.getDisplayName())
                .setInventoryName(inventoryName)
                .buildObject();
        BossTroll.getPlugin(BossTroll.class).getListenerUtil().registerListener(owner, itemClickListener);
        return this;
    }

    /**
     * Finally convert the data to an {@code ItemStack}.
     *
     * @return The final item stack.
     */
    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     * This method builds a pre-defined fill material to
     * have a consistent fill material in all inventories.
     *
     * @return The final item stack.
     */
    public ItemStack buildFillMaterial() {
        final ItemStack fillMaterial = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        final ItemMeta fillMeta = fillMaterial.getItemMeta();
        fillMeta.setDisplayName(" ");
        fillMaterial.setItemMeta(fillMeta);
        return fillMaterial;
    }

}
