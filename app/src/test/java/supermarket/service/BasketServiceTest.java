package supermarket.service;

import org.junit.Before;
import org.junit.Test;
import supermarket.entity.SKU;
import supermarket.entity.SpecialPrice;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BasketServiceTest {

    private BasketService basketService;
    private final SKU A = new SKU("A", 50);
    private final SKU B = new SKU("B", 30);
    private final SKU C = new SKU("C", 20);
    private final SKU D = new SKU("D", 15);

    @Before
    public void setUp(){
        basketService = new BasketService(specialPriceMap());
    }

    @Test
    public void givenKataTaskParameters_whenGetBasketTotal_ShouldReturnTotalOf95(){
        //given
        basketService.addToBasket(B, 1);
        basketService.addToBasket(A, 1);
        basketService.addToBasket(B, 1);

        //when
        Integer basketTotal = basketService.getBasketTotal();

        //should
        assertEquals(95, basketTotal.intValue());
    }

    private Map<SKU, SpecialPrice> specialPriceMap(){
        Map<SKU, SpecialPrice> specialPriceMap = new HashMap<>();
        specialPriceMap.put(A, new SpecialPrice(3, 130));
        specialPriceMap.put(B, new SpecialPrice(2, 45));
        return specialPriceMap;
    }

}