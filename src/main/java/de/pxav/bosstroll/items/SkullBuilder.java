package de.pxav.bosstroll.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

/**
 * This class is similar to the {@code ItemBuilder} with the only
 * difference that you can build skulls with a certain skull owner
 * with this class, because it is using {@code SkullMeta} instead
 * of {@code ItemMeta}.
 *
 * @author pxav
 */
public class SkullBuilder {

    private ItemStack itemStack;
    private SkullMeta skullMeta;

    public SkullBuilder(int subId) {
        this.itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) subId);
        this.skullMeta = (SkullMeta) itemStack.getItemMeta();
    }

    public SkullBuilder setDisplayName(final String displayName) {
        this.skullMeta.setDisplayName(displayName);
        return this;
    }

    public SkullBuilder setAmount(final int amount) {
        this.itemStack.setAmount(amount);
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
