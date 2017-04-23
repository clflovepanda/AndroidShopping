package com.clibchina.shopping.domain.dto;

import com.clibchina.shopping.domain.ShopGoods;
import com.clibchina.shopping.domain.ShopOrderGoodsMapping;

import java.util.List;

public class ShopOrderDto {

    public int id;
    private int userId;
    private String name;
    private int status;
    private double totalPrice;
    private String address;
    private String note;
    private String phone;
    private int sendTime;
    private int ctime;
    private int utime;
    private List<ShopGoodsDto> shopGoodsDtos;

    public List<ShopGoodsDto> getShopGoodsDtos() {
        return shopGoodsDtos;
    }

    public void setShopGoodsDtos(List<ShopGoodsDto> shopGoodsDtos) {
        this.shopGoodsDtos = shopGoodsDtos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSendTime() {
        return sendTime;
    }

    public void setSendTime(int sendTime) {
        this.sendTime = sendTime;
    }

    public int getCtime() {
        return ctime;
    }

    public void setCtime(int ctime) {
        this.ctime = ctime;
    }

    public int getUtime() {
        return utime;
    }

    public void setUtime(int utime) {
        this.utime = utime;
    }
}
