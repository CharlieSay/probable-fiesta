package supermarket.service;

import supermarket.entity.SKU;

import java.util.List;

public class InventoryService {

    private final List<SKU> currentInventory;

    public InventoryService(List<SKU> list) {
        currentInventory = list;
    }

    public List<SKU> getCurrentInventory() {
        return currentInventory;
    }

}
