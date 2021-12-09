package supermarket.entity;

public class SpecialPrice {

    private Integer quantity;
    private Integer price;

    public SpecialPrice(Integer quantity, Integer price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPrice() {
        return price;
    }
}