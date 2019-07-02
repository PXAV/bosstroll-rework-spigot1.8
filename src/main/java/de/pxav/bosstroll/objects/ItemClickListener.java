package de.pxav.bosstroll.objects;

import de.pxav.bosstroll.items.ItemListener;
import lombok.Data;

/**
 * This object is used to save the needed data
 * for an item listener.
 *
 * @see de.pxav.bosstroll.utils.ListenerUtil
 * @see ItemListener
 * @author pxav
 */

@Data
public class ItemClickListener {

    private ItemListener itemListener;
    private String displayName;
    private String inventoryName;

    public static ItemClickListenerBuilder createNew() {
        return new ItemClickListenerBuilder();
    }

    /**
     * This subclass is a builder to build this object easily
     * without accessing the constructor directly.
     */
    public static class ItemClickListenerBuilder {

        private ItemClickListener itemClickListener;

        ItemClickListenerBuilder() {
            this.itemClickListener = new ItemClickListener();
        }

        public ItemClickListenerBuilder setItemListener(ItemListener itemListener) {
            this.itemClickListener.setItemListener(itemListener);
            return this;
        }

        public ItemClickListenerBuilder setDisplayName(String displayName) {
            this.itemClickListener.setDisplayName(displayName);
            return this;
        }

        public ItemClickListenerBuilder setInventoryName(String inventoryName) {
            this.itemClickListener.setInventoryName(inventoryName);
            return this;
        }

        public ItemClickListener buildObject() {
            return this.itemClickListener;
        }

    }

}
