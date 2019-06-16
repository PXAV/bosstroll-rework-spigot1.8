package de.pxav.bosstroll.objects;

import de.pxav.bosstroll.items.ItemListener;
import lombok.Data;

/**
 * A class description goes here.
 *
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

    public static class ItemClickListenerBuilder {

        private ItemClickListener itemClickListener;

        public ItemClickListenerBuilder() {
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
