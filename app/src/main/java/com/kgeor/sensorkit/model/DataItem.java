package com.kgeor.sensorkit.model;


/**
 * DataItem class to get data values for recycler view
 *
 * @author Keegan George
 * @version 1.0
 */
public class DataItem {
    // FIELDS //
    private int itemId;
    private String itemName;

    // CONSTRUCTOR //
    public DataItem() {
    }

    // METHODS //
    public DataItem(int itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    @Override
    public String toString() {
        return "DataItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
