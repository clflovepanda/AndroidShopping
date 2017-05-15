package com.clibchina.shopping.domain.dto;

/**
 * Created by 刘雯雯 on 17/4/18.
 */
public class ShopGoodsDto {

    private int id;
    private String name;
    private String description;
    private int type;
    private String typeContent;
    private int brand;
    private String brandContent;
    private double originPrice;
    private double price;
    private int stock;
    private String pic;
    private int buyTimes;
    private int sign;
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public String getBrandContent() {
        return brandContent;
    }

    public void setBrandContent(String brandContent) {
        this.brandContent = brandContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getBuyTimes() {
        return buyTimes;
    }

    public void setBuyTimes(int buyTimes) {
        this.buyTimes = buyTimes;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "ShopGoods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", brand=" + brand +
                ", originPrice=" + originPrice +
                ", price=" + price +
                ", stock=" + stock +
                ", pic='" + pic + '\'' +
                ", buyTimes=" + buyTimes +
                ", sign=" + sign +
                '}';
    }
}
