package com.clibchina.shopping.controllers;

import com.alibaba.fastjson.JSONObject;
import com.clibchina.shopping.domain.ShopOrder;
import com.clibchina.shopping.domain.ShopOrderGoodsMapping;
import com.clibchina.shopping.service.GoodsService;
import com.clibchina.shopping.service.OrderGoodsMappingService;
import com.clibchina.shopping.service.OrderService;
import com.clibchina.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class OrderController extends PublicController {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderGoodsMappingService orderGoodsMappingService;

    @RequestMapping(value = "/getOrderDetail", method = RequestMethod.GET)
    @ResponseBody
    public void getOrderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @RequestMapping(value = "/acceptOrder", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView accpetOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");
        orderService.updateShopOrderSign(Integer.parseInt(orderId), 1);
        List<ShopOrderGoodsMapping> goodsList = orderGoodsMappingService.getShopOrderGoodsMappingByOrderId(Integer.parseInt(orderId));
        for(ShopOrderGoodsMapping shopCart:goodsList){
            goodsService.reduceShopGoodsStock(shopCart.getGoodsId(),shopCart.getNum());
        }
        return new ModelAndView("redirect:/order");
    }

    @RequestMapping(value = "/hasNewOrder", method = RequestMethod.GET)
    @ResponseBody
    public void hasNewOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ShopOrder> shopOrders = orderService.getShopOrderByStatus(1);
        JSONObject jsonObject = new JSONObject();
        if (shopOrders.isEmpty()) {
            jsonObject.put("status", "ok");
        } else {
            jsonObject.put("status", shopOrders.size());
        }
        writeAjax(response, jsonObject);
        return;
    }

}
