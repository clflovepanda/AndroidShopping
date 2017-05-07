package com.clibchina.shopping.controllers;

import com.alibaba.fastjson.JSON;
import com.clibchina.shopping.domain.ShopGoods;
import com.clibchina.shopping.domain.ShopOrder;
import com.clibchina.shopping.domain.dto.GoodsSaleInfo;
import com.clibchina.shopping.domain.dto.OrderSaleInfo;
import com.clibchina.shopping.service.GoodsService;
import com.clibchina.shopping.service.OrderGoodsMappingService;
import com.clibchina.shopping.service.OrderService;
import com.clibchina.shopping.domain.ShopOrderGoodsMapping;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import com.clibchina.shopping.tools.TimeUtil;

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
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            ShopOrder shopOrder = new ShopOrder();
            shopOrder.setUserId(18);
            shopOrder.setName("杨献彪");
            shopOrder.setStatus(2);
            shopOrder.setTotalPrice(random.nextDouble() * 200);
            shopOrder.setAddress("望京科创大厦A座4楼");
            shopOrder.setNote("尽快送哦!!!");
            shopOrder.setPhone("11111111111");
            shopOrder.setSendTime(0);
            long timestamp = 1493107777000l + random.nextInt(1037000000);
            shopOrder.setCtime((int) (timestamp / 1000));
            shopOrder.setUtime((int) (timestamp / 1000));
            shopOrder.setSign(1);
            shopOrder.setIsDiy(0);
            ShopGoods shopGoods = goodsService.getShopGoods(7 + random.nextInt(8));
            shopOrder.setMsg(shopGoods.getName());
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

    @RequestMapping(value = "/orderSaleInfo")
    @ResponseBody
    public String orderSaleInfo() {
        List<OrderSaleInfo> result = orderService.getSaleInfoList();
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
        for (GoodsSaleInfo info : result) {
            String goodsName = goodsNameMap.get(info.getGoodsId());
            if (StringUtils.isBlank(goodsName)) {
                info.setGoodsName("未知");
            } else {
                info.setGoodsName(goodsName);
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
