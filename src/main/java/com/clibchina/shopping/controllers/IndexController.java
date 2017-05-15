package com.clibchina.shopping.controllers;

import com.clibchina.shopping.domain.ShopBrand;
import com.clibchina.shopping.domain.ShopGoods;
import com.clibchina.shopping.domain.ShopOrder;
import com.clibchina.shopping.domain.ShopType;
import com.clibchina.shopping.domain.dto.ShopGoodsDto;
import com.clibchina.shopping.service.BrandService;
import com.clibchina.shopping.service.GoodsService;
import com.clibchina.shopping.service.OrderService;
import com.clibchina.shopping.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    TypeService typeService;
    @Autowired
    BrandService brandService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "test")
    @ResponseBody
    public Map<String, Object> test() {
        System.out.println("lalalala");
        Map<String, Object> result = new HashMap<>();
        result.put("status", "ok");
        return result;
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public ModelAndView loginPage() {
        return new ModelAndView("register");
    }

//    @RequestMapping(value = "/changePasswordPage")
//    @ResponseBody
//    public ModelAndView changePasswordPage() {
//        return new ModelAndView("change_password");
//    }

//    @RequestMapping(value = "/registerPage")
//    @ResponseBody
//    public ModelAndView registerPage() {
//        return new ModelAndView("register");
//    }

    @RequestMapping(value = "/main")
    @ResponseBody
    public ModelAndView indexPage() {
        return new ModelAndView("main");
    }

    @RequestMapping(value = "/order")
    @ResponseBody
    public ModelAndView orderPage(Model model) {
        List<ShopOrder>shopOrders = orderService.getShopOrderByStatus(1);
        Map<String, Object> result = new HashMap<>();
        result.put("shopOrders", shopOrders);
        model.addAllAttributes(result);
        return new ModelAndView("order");
    }

    @RequestMapping(value = "/classManage")
    @ResponseBody
    public ModelAndView classManegePage(Model model) {
        List<ShopType> shopTypeList = typeService.getAllShopType();
        Map<String, Object> result = new HashMap<>();
        result.put("typeDomains", shopTypeList);
        model.addAllAttributes(result);
        return new ModelAndView("class_manage");
    }

    @RequestMapping(value = "/brandManage")
    @ResponseBody
    public ModelAndView brandManegePage(Model model) {
        List<ShopBrand> brandList = brandService.getAllShopBrand();
        Map<String, Object> result = new HashMap<>();
        result.put("brandDomains", brandList);
        model.addAllAttributes(result);
        return new ModelAndView("brand_manage");
    }

    @RequestMapping(value = "/goodsManage")
    @ResponseBody
    public ModelAndView goodsManegePage(Model model) {
        List<ShopType> shopTypeList = typeService.getAllShopType();
        List<ShopBrand> brandList = brandService.getAllShopBrand();
        List<ShopGoods> goodsList = goodsService.getAllShopGoods();

        Map<Integer, String> typeMap = new HashMap<>();
        Map<Integer, String> brandMap = new HashMap<>();

        for (ShopType shopType : shopTypeList) {
            typeMap.put(shopType.getId(), shopType.getTypeName());
        }

        for (ShopBrand shopBrand : brandList) {
            brandMap.put(shopBrand.getId(), shopBrand.getName());
        }

        List<ShopGoodsDto> shopGoodsDtos = new ArrayList<>();
        for (ShopGoods shopGoods : goodsList) {
            ShopGoodsDto shopGoodsDto = new ShopGoodsDto();
            shopGoodsDto.setId(shopGoods.getId());
            shopGoodsDto.setName(shopGoods.getName());
            shopGoodsDto.setDescription(shopGoods.getDescription());
            shopGoodsDto.setOriginPrice(shopGoods.getOriginPrice());
            shopGoodsDto.setPrice(shopGoods.getPrice());
            shopGoodsDto.setStock(shopGoods.getStock());
            shopGoodsDto.setSign(shopGoods.getSign());
            shopGoodsDto.setBuyTimes(shopGoods.getBuyTimes());
            shopGoodsDto.setPic(shopGoods.getPic());
            shopGoodsDto.setType(shopGoods.getType());
            shopGoodsDto.setBrand(shopGoods.getBrand());
            shopGoodsDto.setTypeContent(typeMap.get(shopGoods.getType()));
            shopGoodsDto.setBrandContent(brandMap.get(shopGoods.getBrand()));
            shopGoodsDtos.add(shopGoodsDto);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("typeDomains", shopTypeList);
        result.put("brandDomains", brandList);
        result.put("goodsDomains", shopGoodsDtos);
        model.addAllAttributes(result);

        return new ModelAndView("goods_manage");
    }

    @RequestMapping(value = "/historyOrder")
    @ResponseBody
    public ModelAndView historyOrderPage(Model model) {
        List<ShopOrder>shopOrders = orderService.getAllShopOrder();
        Map<String, Object> result = new HashMap<>();
        result.put("shopOrders", shopOrders);
        model.addAllAttributes(result);
        return new ModelAndView("history_order");
    }

}
