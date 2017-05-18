package com.clibchina.shopping.controllers;

import com.alibaba.fastjson.JSON;
import com.clibchina.shopping.domain.ShopGoods;
import com.clibchina.shopping.domain.ShopOrder;
import com.clibchina.shopping.domain.ShopOrderGoodsMapping;
import com.clibchina.shopping.domain.dto.GoodsSaleInfo;
import com.clibchina.shopping.domain.dto.OrderSaleInfo;
import com.clibchina.shopping.service.GoodsService;
import com.clibchina.shopping.service.OrderGoodsMappingService;
import com.clibchina.shopping.service.OrderService;
import com.clibchina.shopping.tools.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yxb on 2017/5/7.
 */
@Controller
public class TestController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderGoodsMappingService orderGoodsMappingService;

    @RequestMapping(value = "/insertData")
    @ResponseBody
    public String insertData() {
        for (int i = 0; i < 500; i++) {
            Random random = new Random();
            ShopOrder shopOrder = new ShopOrder();
            shopOrder.setUserId(18);
            shopOrder.setName("刘雯雯");
            shopOrder.setStatus(2);
            shopOrder.setTotalPrice(random.nextDouble() * 200);
            shopOrder.setAddress("黑大C区14栋228");
            shopOrder.setNote("饿死了饿死了");
            shopOrder.setPhone("15545111235");
            shopOrder.setSendTime(0);
            long timestamp = System.currentTimeMillis() - random.nextInt(11*24*3600*1000);
            shopOrder.setCtime((int) (timestamp / 1000));
            shopOrder.setUtime((int) (timestamp / 1000));
            shopOrder.setSign(1);
            shopOrder.setIsDiy(0);
            ShopGoods shopGoods = goodsService.getShopGoods(7 + random.nextInt(8));
            String name="";
            if(null==shopGoods){
                name="鲜花蛋糕";
            }else{
                name=shopGoods.getName();
            }
            shopOrder.setMsg(name);
            shopOrder.setDt(test2(timestamp));
            orderService.addShopOrder(shopOrder);
        }
        return "OK";
    }

    @RequestMapping(value = "/insertMaping")
    @ResponseBody
    public String insertMapData() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            ShopOrderGoodsMapping map = new ShopOrderGoodsMapping();
            ShopGoods shopGoods = goodsService.getShopGoods(7 + random.nextInt(10));
            map.setGoodsId(shopGoods.getId());
            map.setOrderId(random.nextInt()*1000);
            map.setNum(1);
            map.setCtime(TimeUtil.getNow());
            map.setUtime(TimeUtil.getNow());
            orderGoodsMappingService.addShopOrderGoodsMapping(map);
        }
        return "OK";
    }

    @RequestMapping(value = "/orderSaleInfo") //接收首页请求
    @ResponseBody
    public String orderSaleInfo() {
        List<OrderSaleInfo> result = orderService.getSaleInfoList(); //查库得到所有销售额订单量信息
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/orderTodaySaleInfo")
    @ResponseBody
    public String orderTodaySaleInfo() {
        List<OrderSaleInfo> result = orderService.getTodaySaleInfoList();
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/goodsSaleInfo")
    @ResponseBody
    public String goodsSaleInfo() {

        List<GoodsSaleInfo> result = orderGoodsMappingService.getGoodsSaleInfo();
        List<ShopGoods> shopGoods = goodsService.getAllShopGoods();
        HashMap<Integer, String> goodsNameMap = new HashMap<Integer, String>();
        for (ShopGoods sg : shopGoods) {
            goodsNameMap.put(sg.getId(), sg.getName());
        }
        Iterator iterator = result.iterator();
        while(iterator.hasNext()){
            GoodsSaleInfo temp= (GoodsSaleInfo) iterator.next();
            String goodsName = goodsNameMap.get(temp.getGoodsId());
            if (StringUtils.isBlank(goodsName)) {
                iterator.remove();
            } else {
                temp.setGoodsName(goodsName);
            }
        }
        return JSON.toJSONString(result);
    }

    private String test2(long times) {
        Date date = new Date(times);
        try {
//            SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            SimpleDateFormat ss = new SimpleDateFormat("yyyyMMdd");
            return ss.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

}
