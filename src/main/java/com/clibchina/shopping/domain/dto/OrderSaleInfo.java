package com.clibchina.shopping.domain.dto;

/**
 * Created by yxb on 2017/5/7.
 */
public class OrderSaleInfo {
    private String dt;
    private int orderNum;
    private double totalPrice;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
