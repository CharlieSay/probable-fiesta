package supermarket.service;

import supermarket.entity.SKU;
import supermarket.entity.SpecialPrice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class BasketService {

    private Map<SKU, Integer> basket;
    private Map<SKU, SpecialPrice> specialPricingRule;

    public BasketService(Map<SKU, SpecialPrice> specialPricingRule) {
        basket = new HashMap<>();
        this.specialPricingRule = specialPricingRule;
    }

    public Map<SKU, Integer> addToBasket(SKU sku, Integer quantity) {
        if (!basket.containsKey(sku)) {
            basket.put(sku, quantity);
        } else {
            basket.put(sku, quantity + basket.get(sku));
        }
        return basket;
    }

    public Integer getBasketTotal() {
        AtomicReference<Integer> total = new AtomicReference<>(0);

        basket.forEach((sku, quantity) -> {
            if (specialPricingRule.containsKey(sku)) {
                SpecialPrice specialPrice = specialPricingRule.get(sku);
                if (quantity < specialPrice.getQuantity()) {
                    total.updateAndGet(v -> v + (quantity) * sku.getUnitPrice());
                } else if (quantity.equals(specialPrice.getQuantity())) {
                    total.updateAndGet(v -> v + specialPrice.getPrice());
                }
            } else {
                total.updateAndGet(v -> v + sku.getUnitPrice());
            }
        });
        return total.get();
    }

    public void clearBasket(){
        basket.clear();
    }
}
