package de.pxav.bosstroll.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class SkullBuilder {

    private ItemStack itemStack;
    private SkullMeta skullMeta;

    public SkullBuilder() {
        this.itemStack = new ItemStack(Material.SKULL_ITEM, 3);
        this.skullMeta = (SkullMeta) itemStack.getItemMeta();
    }

    public SkullBuilder setDisplayName(final String displayName) {
        this.skullMeta.setDisplayName(displayName);
        return this;
    }

    public SkullBuilder setLore(final String[] lore) {
        this.skullMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public SkullBuilder setSkullOwner(final String playerName) {
        this.skullMeta.setOwner(playerName);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

}
