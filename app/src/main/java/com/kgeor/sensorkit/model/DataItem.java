package com.kgeor.sensorkit.model;

import java.util.UUID;

public class DataItem {

    private String itemId;
    private String itemName;

    public DataItem() {
    }

    public DataItem(String itemId, String itemName) {
        // when creating a new object without UUID, assign it one
        if (itemId == null) {
            itemId = UUID.randomUUID().toString();
        }

        this.itemId = itemId;
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
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
