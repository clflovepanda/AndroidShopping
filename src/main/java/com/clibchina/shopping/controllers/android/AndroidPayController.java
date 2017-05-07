package com.clibchina.shopping.controllers.android;

import com.alibaba.fastjson.JSONObject;
import com.clibchina.shopping.controllers.PublicController;
import com.clibchina.shopping.domain.ShopCart;
import com.clibchina.shopping.domain.ShopGoods;
import com.clibchina.shopping.domain.ShopOrder;
import com.clibchina.shopping.domain.ShopOrderGoodsMapping;
import com.clibchina.shopping.service.BrandService;
import com.clibchina.shopping.service.CUserService;
import com.clibchina.shopping.service.CartService;
import com.clibchina.shopping.service.GoodsService;
import com.clibchina.shopping.service.OrderGoodsMappingService;
import com.clibchina.shopping.service.OrderService;
import com.clibchina.shopping.service.TypeService;
import com.clibchina.shopping.tools.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/android")
public class AndroidPayController extends PublicController {

    @Autowired
    TypeService typeService;
    @Autowired
    BrandService brandService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    CUserService cUserService;
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderGoodsMappingService orderGoodsMappingService;

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    @ResponseBody
    public void pay(@RequestParam("userName") String userName,
                    @RequestParam("userId") int userId,
                    @RequestParam("msg") String msg,
                    @RequestParam("receiver") String receiver,
                    @RequestParam("phone") String phone,
                    @RequestParam("address") String address,
                    HttpServletResponse response) throws IOException {

        List<ShopCart> shopCartList = cartService.getShopCartListByUserId(userId);
        double totalPrice = 0;
        StringBuffer msgInfo = new StringBuffer();
        for (ShopCart shopCart : shopCartList) {
            ShopGoods shopGoods = goodsService.getShopGoods(shopCart.getGoodsId());
            msgInfo.append(","+shopGoods.getName());
//            goodsService.reduceShopGoodsStock(shopCart.getGoodsId(),shopCart.getNum());
            totalPrice += shopGoods.getPrice() * shopCart.getNum();

        }

        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setUserId(userId);
        shopOrder.setName(receiver);
        shopOrder.setStatus(1);
        shopOrder.setTotalPrice(totalPrice);
        shopOrder.setAddress(address);
        shopOrder.setNote(msg);
        shopOrder.setPhone(phone);
        shopOrder.setSendTime(0);
        shopOrder.setCtime(TimeUtil.getNow());
        shopOrder.setUtime(TimeUtil.getNow());
        shopOrder.setMsg(msgInfo.substring(1));
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        shopOrder.setDt(df.format(new Date()));
        orderService.addShopOrder(shopOrder);

        for (ShopCart shopCart : shopCartList) {
            ShopOrderGoodsMapping shopOrderGoodsMapping = new ShopOrderGoodsMapping();
            shopOrderGoodsMapping.setGoodsId(shopCart.getGoodsId());
            shopOrderGoodsMapping.setNum(shopCart.getNum());
            shopOrderGoodsMapping.setOrderId(shopOrder.getId());
            shopOrderGoodsMapping.setCtime(TimeUtil.getNow());
            shopOrderGoodsMapping.setUtime(TimeUtil.getNow());
            orderGoodsMappingService.addShopOrderGoodsMapping(shopOrderGoodsMapping);
        }

        cartService.deleteShopCartByUserId(userId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");
        jsonObject.put("orderId", shopOrder.getId());
        jsonObject.put("totalPrice", shopOrder.getTotalPrice());
        writeAjax(response, jsonObject);
        return;
    }

    @RequestMapping(value = "/payDiy", method = RequestMethod.GET)
    @ResponseBody
    public void payDiy(@RequestParam("userName") String userName,
                       @RequestParam("userId") int userId,
                       @RequestParam("msg") String msg,
                       @RequestParam("receiver") String receiver,
                       @RequestParam("phone") String phone,
                       @RequestParam("address") String address,
                       @RequestParam("result") String result,
                       HttpServletResponse response) throws IOException {

        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setUserId(userId);
        shopOrder.setName(receiver);
        shopOrder.setStatus(1);
        shopOrder.setTotalPrice(200);
        shopOrder.setAddress(address);
        shopOrder.setNote(msg);
        shopOrder.setPhone(phone);
        shopOrder.setSendTime(0);
        shopOrder.setCtime(TimeUtil.getNow());
        shopOrder.setUtime(TimeUtil.getNow());
        shopOrder.setIsDiy(1);
        shopOrder.setMsg(result);
        orderService.addShopOrder(shopOrder);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");
        jsonObject.put("orderId", shopOrder.getId());
        jsonObject.put("totalPrice", shopOrder.getTotalPrice());
        writeAjax(response, jsonObject);
        return;
    }

}
