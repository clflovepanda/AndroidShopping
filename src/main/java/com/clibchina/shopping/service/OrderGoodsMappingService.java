package com.clibchina.shopping.service;

import com.clibchina.shopping.dao.ShopOrderGoodsMappingDao;
import com.clibchina.shopping.domain.ShopOrderGoodsMapping;
import com.clibchina.shopping.domain.dto.GoodsSaleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TimeZone;

@Service
public class OrderGoodsMappingService {

    @Autowired
    ShopOrderGoodsMappingDao shopOrderGoodsMappingDao;

    public List<ShopOrderGoodsMapping> getAllShopOrderGoodsMapping() {
        List<ShopOrderGoodsMapping> shopOrderGoodsMappings = shopOrderGoodsMappingDao.queryAllShopOrderGoodsMapping();
        return shopOrderGoodsMappings;
    }

    public ShopOrderGoodsMapping getShopOrderGoodsMapping(int id) {
        ShopOrderGoodsMapping shopOrderGoodsMapping = shopOrderGoodsMappingDao.queryShopOrderGoodsMapping(id);
        return shopOrderGoodsMapping;
    }

    public List<ShopOrderGoodsMapping> getShopOrderGoodsMappingByOrderId(int orderId) {
        List<ShopOrderGoodsMapping> shopOrderGoodsMappings = shopOrderGoodsMappingDao.queryShopOrderGoodsMappingByOrderId(orderId);
        return shopOrderGoodsMappings;
    }

    public void addShopOrderGoodsMapping(ShopOrderGoodsMapping shopOrderGoodsMapping) {
        shopOrderGoodsMappingDao.addShopOrderGoodsMapping(shopOrderGoodsMapping);
    }

    public void deleteShopOrderGoodsMapping(int id) {
        shopOrderGoodsMappingDao.deleteShopOrderGoodsMapping(id);
    }

    public void updateShopOrderGoodsMapping(ShopOrderGoodsMapping shopOrderGoodsMapping) {
        shopOrderGoodsMappingDao.updateShopOrderGoodsById(shopOrderGoodsMapping);
    }
    public List<GoodsSaleInfo> getGoodsSaleInfo(){
        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        int startTime = (int) (zero/1000)-10*24*3600;
        List<GoodsSaleInfo> list = shopOrderGoodsMappingDao.countGoodsSaleInfo(startTime);
        return list;

    }

}
