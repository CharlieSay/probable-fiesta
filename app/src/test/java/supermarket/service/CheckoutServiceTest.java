package supermarket.service;

import org.junit.Before;
import org.junit.Test;
import supermarket.entity.SKU;
import supermarket.entity.SpecialPrice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;
    private final SKU foo = new SKU("foo", 10);
    private final SKU bar = new SKU("bar", 20);

    @Before
    public void setUp(){
        checkoutService = new CheckoutService(specialPriceMap(),mockInventoryService());
    }

    @Test
    public void givenSKUExists_WhenScanItem_ShouldCallBasketServiceAddToBasket() throws Exception {
        Map<SKU, Integer> currentBasket = checkoutService.scanItem("foo");

        assertTrue(currentBasket.containsKey(foo));
    }

    @Test(expected = Exception.class)
    public void givenSKUEDoesNotExist_WhenScanItem_ShouldCThrowException() throws Exception {
        checkoutService.scanItem("fasdfasdf");
    }

    @Test
    public void whenCheckout_ShouldReturnTrue(){
        Boolean checkout = checkoutService.checkout();

        assertTrue(checkout);
    }

    private Map<SKU, SpecialPrice> specialPriceMap(){
        Map<SKU, SpecialPrice> specialPriceMap = new HashMap<>();
        specialPriceMap.put(foo, new SpecialPrice(3, 25));
        return specialPriceMap;
    }

    private InventoryService mockInventoryService() {
        return new InventoryService(List.of(foo, bar));
    }

}
