package de.pxav.bosstroll.items;

import de.pxav.bosstroll.BossTroll;
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
        this.itemStack = new ItemStack(type, (short) subId);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayName(final String displayName) {
        this.itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder setLore(final String[] lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder addListener(final Player owner, final ItemListener listener) {
        BossTroll.getPlugin(BossTroll.class).getListenerUtil().registerListener(owner, listener);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
