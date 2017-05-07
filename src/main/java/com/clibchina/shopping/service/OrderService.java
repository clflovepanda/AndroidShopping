package com.clibchina.shopping.service;

import com.clibchina.shopping.dao.ShopOrderDao;
import com.clibchina.shopping.domain.ShopOrder;
import com.clibchina.shopping.domain.dto.OrderSaleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TimeZone;

@Service
public class OrderService {

    @Autowired
    ShopOrderDao shopOrderDao;

    public List<ShopOrder> getAllShopOrder() {
        List<ShopOrder> shopOrders = shopOrderDao.queryAllShopOrder();
        return shopOrders;
    }

    public List<ShopOrder> getShopOrderByStatus(int status) {
        List<ShopOrder> shopOrders = shopOrderDao.queryShopOrderByStatus(status);
        return shopOrders;
    }

    public ShopOrder getShopOrder(int id) {
        ShopOrder shopOrder = shopOrderDao.queryShopOrderById(id);
        return shopOrder;
    }

    public List<ShopOrder> getShopOrderByUserId(int userId) {
        List<ShopOrder> shopOrders = shopOrderDao.queryShopOrderByUserId(userId);
        return shopOrders;
    }

    public int addShopOrder(ShopOrder shopOrder) {
        return shopOrderDao.addShopOrder(shopOrder);
    }

    public void deleteShopOrder(int id) {
        shopOrderDao.deleteShopOrder(id);
    }

    public void updateShopOrder(ShopOrder shopOrder) {
        shopOrderDao.updateShopOrderStatusById(shopOrder.getId(), shopOrder.getStatus());
    }

    public void updateShopOrderSign(int orderId, int sign) {
        shopOrderDao.updateShopOrderSignById(orderId, sign);
    }

    public List<OrderSaleInfo> getSaleInfoList() {
        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        int endTime = (int) (zero/1000);
        int startTime = endTime-10*24*3600;
        List<OrderSaleInfo> orderSaleInfos = shopOrderDao.countSaleCountInfo(startTime, endTime);
        return orderSaleInfos;
    }
}
