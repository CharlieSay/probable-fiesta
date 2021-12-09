package supermarket.service;

import supermarket.entity.SKU;
import supermarket.entity.SpecialPrice;

import java.util.Map;
import java.util.Optional;

public class CheckoutService {

    private BasketService basketService;
    private InventoryService inventoryService;

    public CheckoutService(Map<SKU, SpecialPrice> specialPricingRule, InventoryService inventoryService) {
        this.basketService = new BasketService(specialPricingRule);
        this.inventoryService = inventoryService;
    }

    public Map<SKU, Integer> scanItem(String skuName) throws Exception {
        Optional<SKU> optionalSKU = inventoryService.getCurrentInventory().stream().filter(sku -> sku.getUnitName().equalsIgnoreCase(skuName)).findFirst();
        if (optionalSKU.isEmpty()) {
            throw new Exception("Not a valid item");
        }else{
            return basketService.addToBasket(optionalSKU.get(), 1);
        }
    }

    public Boolean checkout(){
        System.out.println("Pretend checkout" + basketService.getBasketTotal());
        basketService.clearBasket();
        return true;
    }

}