package de.pxav.bosstroll.items;

import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * This interface contains the {@code #onClick()} method
 * which defines what should happen when a player clicks an item.
 *
 * @author pxav
 */
public interface ItemListener {

    /**
     * This method is executed when a player clicks
     * a certain item in an inventory, which was built
     * with the {@code ItemBuilder}
     *
     * @param event The event object which is used to pass the
     *              data (like inventory name, etc.) to the actual usage.
     *              This can also be used to cancel the event.
     */
    void onClick(final InventoryClickEvent event);

}
