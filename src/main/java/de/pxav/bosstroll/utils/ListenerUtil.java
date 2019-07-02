package de.pxav.bosstroll.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.pxav.bosstroll.BossTroll;
import de.pxav.bosstroll.items.ItemListener;
import de.pxav.bosstroll.objects.ItemClickListener;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * This class administrates the internal listeners. This means it
 * registers and unregisters new and old listeners and fires
 * existing ones when they are needed.
 *
 * @author pxav
 */
public class ListenerUtil {

    @Getter
    private List<ItemClickListener> listeners;

    private Map<UUID, List<ItemClickListener>> playerListeners;

    // instance of the main class
    private BossTroll main;

    /**
     * Default constructor.
     *
     * @param main The instance of the main class.
     */
    public ListenerUtil(final BossTroll main) {
        this.main = main;
        this.listeners = new ArrayList<>();
        this.playerListeners = new HashMap<>();
    }

    /**
     * This method registers a new listener.
     *
     * @param owner         The player who owns the inventory which contains
     *                      the item that should be listened for.
     * @param itemListener  The {@code ItemClickListener} object, which also defines
     *                      what should happen when the item is clicked.
     */
    public void registerListener(final Player owner, final ItemClickListener itemListener) {
        this.playerListeners.computeIfAbsent(owner.getUniqueId(), k -> new ArrayList<>());
        List<ItemClickListener> itemClickListeners = new ArrayList<>(this.playerListeners.get(owner.getUniqueId()));
        itemClickListeners.add(itemListener);
        this.playerListeners.put(owner.getUniqueId(), itemClickListeners);
        this.listeners.add(itemListener);
    }

    /**
     * Removes all listeners which belong to a certain player,
     * so that they cannot be fired anymore.
     *
     * @param owner The player whose listeners should be removed.
     */
    public void unregisterListener(final Player owner) {
        this.playerListeners.forEach((uuid, itemClickListeners) -> {
            if (owner.getUniqueId().toString().equalsIgnoreCase(uuid.toString()))
                this.listeners.removeAll(itemClickListeners);
        });
        this.playerListeners.remove(owner.getUniqueId());
    }

    /**
     * The defined code of a certain listener is executed.
     *
     * @param event The event which fired the listener.
     *              This event needs to contain the data for the defined code.
     */
    public void fire(final InventoryClickEvent event) {
        Lists.newArrayList(listeners).forEach(current -> {
            if(event.getInventory().getName().equalsIgnoreCase(current.getInventoryName())
                && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(current.getDisplayName()))
            current.getItemListener().onClick(event);
        });
    }

}