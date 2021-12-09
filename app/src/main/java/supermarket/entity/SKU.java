package supermarket.entity;

public class SKU {

    private String unitName;
    private Integer unitPrice;

    public SKU(String unitName, Integer unitPrice) {
        this.unitName = unitName;
        this.unitPrice = unitPrice;
    }

    public String getUnitName() {
        return unitName;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

}
