package com.clibchina.shopping.domain.dto;

/**
 * Created by yxb on 2017/5/7.
 */
public class GoodsSaleInfo {
    private int goodsId;
    private String goodsName;
    private int saleNum;


    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }
}
