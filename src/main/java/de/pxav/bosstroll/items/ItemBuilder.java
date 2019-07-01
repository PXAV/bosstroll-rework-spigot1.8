package de.pxav.bosstroll.items;

import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.objects.ItemClickListener;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.ItemList;

import java.util.Arrays;

/**
 * A class description goes here.
 *
 * @author pxav
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

    public ItemBuilder setDisplayName(final String displayName) {
        this.itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder setAmount(final int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setLore(final String[] lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

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

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack buildFillMaterial() {
        final ItemStack fillMaterial = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        final ItemMeta fillMeta = fillMaterial.getItemMeta();
        fillMeta.setDisplayName(" ");
        fillMaterial.setItemMeta(fillMeta);
        return fillMaterial;
    }

}
