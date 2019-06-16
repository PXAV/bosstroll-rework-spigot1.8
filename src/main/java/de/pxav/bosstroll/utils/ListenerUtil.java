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

/**
 * A class description goes here.
 *
 * @author pxav
 */
public class ListenerUtil {

    @Getter
    private List<ItemClickListener> listeners;

    private Map<UUID, ItemClickListener> playerListeners;

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

    public void registerListener(final Player owner, final ItemClickListener itemListener) {
        this.playerListeners.put(owner.getUniqueId(), itemListener);
        this.listeners.add(itemListener);
    }

    public void unregisterListener(final Player owner) {
        Maps.newHashMap(this.playerListeners).keySet().forEach(current -> {
            if(current.toString().equalsIgnoreCase(owner.getUniqueId().toString()))
                this.unregisterListener(this.playerListeners.get(current));
        });
    }

    public void fire(final InventoryClickEvent event) {
        Lists.newArrayList(listeners).forEach(current -> {
            if(event.getInventory().getName().equalsIgnoreCase(current.getInventoryName())
                && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(current.getDisplayName()))
            current.getItemListener().onClick(event);
        });
    }

    private void unregisterListener(final ItemClickListener itemListener) {
        this.listeners.remove(itemListener);
    }

}