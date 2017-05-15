package com.clibchina.shopping.controllers;

import com.alibaba.fastjson.JSONObject;
import com.clibchina.shopping.domain.ShopGoods;
import com.clibchina.shopping.service.BrandService;
import com.clibchina.shopping.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GoodsController extends PublicController {

    @Autowired
    BrandService brandService;
    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "/addGoods", method = RequestMethod.GET)
    @ResponseBody
    public void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String type = request.getParameter("type");
        String brand = request.getParameter("brand");
        String originPrice = request.getParameter("originPrice");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
        String sign = request.getParameter("sign");
        ShopGoods shopGoods = new ShopGoods();
        shopGoods.setName(name);
        shopGoods.setDescription(description);
        shopGoods.setType(Integer.parseInt(type));
        shopGoods.setBrand(Integer.parseInt(brand));
        shopGoods.setOriginPrice(Double.parseDouble(originPrice));
        shopGoods.setPrice(Double.parseDouble(price));
        shopGoods.setStock(Integer.parseInt(stock));
        shopGoods.setSign(Integer.parseInt(sign));
        shopGoods.setPic("/android/img/33.jpg");
        shopGoods.setBuyTimes(0);
        goodsService.addShopGoods(shopGoods);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");
        writeAjax(response, jsonObject);
        return;
    }

    @RequestMapping(value = "/deleteGoods")
    @ResponseBody
    public ModelAndView deleteGoods(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        goodsService.deleteShopGoods(id);
        return new ModelAndView("redirect:/goodsManage");
    }

    @RequestMapping(value = "/editGoods", method = RequestMethod.GET)
    @ResponseBody
    public void editGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String type = request.getParameter("type");
        String brand = request.getParameter("brand");
        String originPrice = request.getParameter("originPrice");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
        String sign = request.getParameter("sign");
        ShopGoods shopGoods = goodsService.getShopGoods(Integer.parseInt(id));
        shopGoods.setName(name);
        shopGoods.setDescription(description);
        shopGoods.setType(Integer.parseInt(type));
        shopGoods.setBrand(Integer.parseInt(brand));
        shopGoods.setOriginPrice(Double.parseDouble(originPrice));
        shopGoods.setPrice(Double.parseDouble(price));
        shopGoods.setStock(Integer.parseInt(stock));
        shopGoods.setSign(Integer.parseInt(sign));
        goodsService.updateShopGoods(shopGoods);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "ok");
        writeAjax(response, jsonObject);
        return;
    }
}
