package com.clibchina.shopping.controllers.android;

import com.clibchina.shopping.domain.*;
import com.clibchina.shopping.domain.dto.ShopCartDto;
import com.clibchina.shopping.domain.dto.ShopGoodsDto;
import com.clibchina.shopping.domain.dto.ShopOrderDto;
import com.clibchina.shopping.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/android")
public class MainController {

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

    @RequestMapping(value = "/main")
    @ResponseBody
    public ModelAndView mainPage(Model model, @RequestParam("userName") String userName) {
        ShopUser shopUser = cUserService.queryShopUserByUserName(userName); // 定义一个shopUser对象,包括根据用户查询出来的用户信息;
        List<ShopType> shopTypeList = typeService.getAllShopType();  // 定义一个list,包含查询出来的所有商品类型
        List<ShopBrand> brandList = brandService.getAllShopBrand();  // 定义一个list,包含查询出来的所有商品品牌
        List<ShopGoods> goodsList = goodsService.getAllShopGoods();  // 定义一个list,包含查询出来的所有商品
        List<ShopGoodsDto> shopGoodsDtos = getShopGoodsDtos(goodsList, shopTypeList, brandList); //调用私有方法,将查处所有信息封装到一个list

        Map<String, Object> result = new HashMap<>();
        result.put("typeDomains", shopTypeList);
        result.put("brandDomains", brandList);
        result.put("goodsDomains", shopGoodsDtos);
        result.put("userName", shopUser.getName());
        result.put("userId", shopUser.getId());
        model.addAllAttributes(result);

        return new ModelAndView("android/main");
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public ModelAndView search(Model model, @RequestParam("userName") String userName,
                                            @RequestParam("userId") int userId,
                                            @RequestParam("searchMsg") String  searchMsg) {
        List<ShopType> shopTypeList = typeService.getAllShopType();  // 定义一个list,包含查询出来的所有商品类型
        List<ShopBrand> brandList = brandService.getAllShopBrand();  // 定义一个list,包含查询出来的所有商品品牌
        List<ShopGoods> goodsList = goodsService.getAllShopGoodsBysearch(searchMsg);  // 定义一个list,包含查询出来的所有商品
        List<ShopGoodsDto> shopGoodsDtos = getShopGoodsDtos(goodsList, shopTypeList, brandList); //调用私有方法,将查处所有信息封装到一个list

        Map<String, Object> result = new HashMap<>();
        result.put("typeDomains", shopTypeList);
        result.put("brandDomains", brandList);
        result.put("goodsDomains", shopGoodsDtos);
        result.put("userName", userName);
        result.put("userId", userId);
        model.addAllAttributes(result);

        return new ModelAndView("android/main");
    }

    @RequestMapping(value = "/typeList")
    @ResponseBody
    public ModelAndView typeListPage(Model model,
                                     @RequestParam("type") int type,
                                     @RequestParam("userName") String userName,
                                     @RequestParam("userId") int userId) {
        ShopType shopType = typeService.getShopType(type);
        List<ShopType> shopTypeList = typeService.getAllShopType();
        List<ShopBrand> brandList = brandService.getAllShopBrand();
        List<ShopGoods> shopGoodsList = goodsService.getShopGoodsesByTypeId(type);
    List<ShopGoodsDto> shopGoodsDtos = getShopGoodsDtos(shopGoodsList, shopTypeList, brandList);

        Map<String, Object> result = new HashMap<>();
        result.put("shopType", shopType);
        result.put("typeDomains", shopTypeList);
        result.put("brandDomains", brandList);
        result.put("goodsDomains", shopGoodsDtos);
        result.put("userName", userName);
        result.put("userId", userId);
        model.addAllAttributes(result);
        return new ModelAndView("android/typeList");
    }

    @RequestMapping(value = "/diyTypeList")
    @ResponseBody
    public ModelAndView diyTypeListPage(Model model,
                                     @RequestParam("type") int type,
                                     @RequestParam("userName") String userName,
                                     @RequestParam("userId") int userId) {
        ShopType shopType = typeService.getShopType(type);
        List<ShopType> shopTypeList = typeService.getAllShopType();
        List<ShopBrand> brandList = brandService.getAllShopBrand();
        List<ShopGoods> shopGoodsList = goodsService.getShopGoodsesByTypeId(type);
        List<ShopGoodsDto> shopGoodsDtos = getShopGoodsDtos(shopGoodsList, shopTypeList, brandList);

        Map<String, Object> result = new HashMap<>();
        result.put("shopType", shopType);
        result.put("typeDomains", shopTypeList);
        result.put("brandDomains", brandList);
        result.put("goodsDomains", shopGoodsDtos);
        result.put("userName", userName);
        result.put("userId", userId);
        model.addAllAttributes(result);
        return new ModelAndView("android/diyTypeList");
    }

    @RequestMapping(value = "/goodsDetail")
    @ResponseBody
    public ModelAndView goodsDetailPage(Model model,
                                        @RequestParam("goodsId") int goodsId,
                                        @RequestParam("type") int type,
                                        @RequestParam("userName") String userName,
                                        @RequestParam("userId") int userId) {
        Map<String, Object> result = new HashMap<>();
        result.put("userName", userName);
        result.put("userId", userId);
        model.addAllAttributes(result);
        return new ModelAndView("android/goodsDetail");
    }

    @RequestMapping(value = "/shopping")
    @ResponseBody
    public ModelAndView shoppingPage(Model model,
                                     @RequestParam("userName") String userName,
                                     @RequestParam("userId") int userId) {

        List<ShopCart> shopCartList = cartService.getShopCartListByUserId(userId);

        List<ShopCartDto> shopCartDtos = new ArrayList<>();
        double totalPrice = 0;
        for (ShopCart shopCart : shopCartList) {
            ShopCartDto shopCartDto = new ShopCartDto();
            ShopGoods shopGoods = goodsService.getShopGoods(shopCart.getGoodsId());
            if(null == shopGoods) {
                continue;
            }
            shopCartDto.setId(shopCart.getId());
            shopCartDto.setNum(shopCart.getNum());
            shopCartDto.setGoodsId(shopCart.getGoodsId());
            shopCartDto.setUserId(shopCart.getUserId());
            shopCartDto.setGoodsName(shopGoods.getName());
            shopCartDto.setGoodsPic(shopGoods.getPic());
            shopCartDto.setGoodsPrice(shopGoods.getPrice());
            shopCartDtos.add(shopCartDto);
            totalPrice += shopCartDto.getGoodsPrice() * shopCartDto.getNum();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("userName", userName);
        result.put("userId", userId);
        result.put("cartDomains", shopCartDtos);
        result.put("totalPrice", totalPrice);
        model.addAllAttributes(result);
        return new ModelAndView("android/shopping");
    }

    @RequestMapping(value = "/payPage")
    @ResponseBody
    public ModelAndView payPage(Model model,
                                @RequestParam("userName") String userName,
                                @RequestParam("userId") int userId) {
        List<ShopCart> shopCartList = cartService.getShopCartListByUserId(userId);

        List<ShopCartDto> shopCartDtos = new ArrayList<>();
        double totalPrice = 0;
        for (ShopCart shopCart : shopCartList) {
            ShopCartDto shopCartDto = new ShopCartDto();
            ShopGoods shopGoods = goodsService.getShopGoods(shopCart.getGoodsId());
            if(null == shopGoods) {
                continue;
            }
            shopCartDto.setId(shopCart.getId());
            shopCartDto.setNum(shopCart.getNum());
            shopCartDto.setGoodsId(shopCart.getGoodsId());
            shopCartDto.setUserId(shopCart.getUserId());
            shopCartDto.setGoodsName(shopGoods.getName());
            shopCartDto.setGoodsPic(shopGoods.getPic());
            shopCartDto.setGoodsPrice(shopGoods.getPrice());
            shopCartDtos.add(shopCartDto);
            totalPrice += shopCartDto.getGoodsPrice() * shopCartDto.getNum();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("userName", userName);
        result.put("userId", userId);
        result.put("cartDomains", shopCartDtos);
        result.put("totalPrice", totalPrice);
        model.addAllAttributes(result);
        return new ModelAndView("android/pay");
    }

    @RequestMapping(value = "/successful")
    @ResponseBody
    public ModelAndView successfulPage(Model model,
                                       @RequestParam("userName") String userName,
                                       @RequestParam("userId") int userId,
                                       @RequestParam("totalPrice") double totalPrice,
                                       @RequestParam("orderId") int orderId) {

        Map<String, Object> result = new HashMap<>();
        result.put("userName", userName);
        result.put("userId", userId);
        result.put("totalPrice", totalPrice);
        result.put("orderId", orderId);
        model.addAllAttributes(result);
        return new ModelAndView("android/successful");
    }

    @RequestMapping(value = "/loginPage")
    @ResponseBody
    public ModelAndView loginPage() {
        return new ModelAndView("android/login");
    }

    @RequestMapping(value = "/registerPage")
    @ResponseBody
    public ModelAndView registerPage() {
        return new ModelAndView("android/register");
    }

    @RequestMapping(value = "/changePasswordPage")
    @ResponseBody
    public ModelAndView changePasswordPage() {
        return new ModelAndView("android/backPassword");
    }

    @RequestMapping(value = "/myList") //订单
    @ResponseBody
    public ModelAndView myListPage(Model model,
                                   @RequestParam("userName") String userName,
                                   @RequestParam("userId") int userId,
                                   @RequestParam("type") int type) {

        List<ShopOrder> shopOrders = orderService.getShopOrderByUserId(userId);  //这里的ShopOrder是domain里封装的数据库订单类
        if (shopOrders.isEmpty()) {
            Map<String, Object> result = new HashMap<>();
            result.put("userName", userName);
            result.put("userId", userId);
            model.addAllAttributes(result);
            return new ModelAndView("android/myList");
        }
        List<ShopType> shopTypeList = typeService.getAllShopType();
        List<ShopBrand> brandList = brandService.getAllShopBrand();

        Map<Integer, String> typeMap = new HashMap<>();
        Map<Integer, String> brandMap = new HashMap<>();

        for (ShopType shopType : shopTypeList) {
            typeMap.put(shopType.getId(), shopType.getTypeName());
        }

        for (ShopBrand shopBrand : brandList) {
            brandMap.put(shopBrand.getId(), shopBrand.getName());
        }

        List<ShopOrderDto> shopOrderDtos = new ArrayList<>(); //存放所有订单
        for (ShopOrder shopOrder : shopOrders) { // 循环每个订单
            if (type == -1 || type == shopOrder.getStatus()) {   //type是传值订单类型  -1全部订单
                ShopOrderDto shopOrderDto = new ShopOrderDto();
                List<ShopOrderGoodsMapping> shopOrderGoodsMappings  = orderGoodsMappingService.getShopOrderGoodsMappingByOrderId(shopOrder.getId());
                List<ShopGoodsDto> shopGoodsDtos = new ArrayList<>(); // 定义一个数组,存放对应订单下商品
                for (ShopOrderGoodsMapping shopOrderGoodsMapping : shopOrderGoodsMappings) { // 循环每个订单下商品
                    ShopGoods shopGoods = goodsService.getShopGoods(shopOrderGoodsMapping.getGoodsId());// 通过商品id拿到具体商品信息
                   if(null == shopGoods) {
                       continue;
                   }
                    ShopGoodsDto shopGoodsDto = new ShopGoodsDto();
                    shopGoodsDto.setId(shopGoods.getId());
                    shopGoodsDto.setBrand(shopGoods.getBrand());
                    shopGoodsDto.setPic(shopGoods.getPic());
                    shopGoodsDto.setBuyTimes(shopGoods.getBuyTimes());
                    shopGoodsDto.setName(shopGoods.getName());
                    shopGoodsDto.setNum(shopOrderGoodsMapping.getNum());
                    shopGoodsDto.setDescription(shopGoods.getDescription());
                    shopGoodsDto.setOriginPrice(shopGoods.getOriginPrice());
                    shopGoodsDto.setPrice(shopGoods.getPrice());
                    shopGoodsDto.setSign(shopGoods.getSign());
                    shopGoodsDto.setType(shopGoods.getType());
                    shopGoodsDto.setStock(shopGoods.getStock());
                    shopGoodsDto.setBrandContent(brandMap.get(shopGoods.getBrand()));
                    shopGoodsDto.setTypeContent(typeMap.get(shopGoods.getType()));
                    shopGoodsDtos.add(shopGoodsDto);
                }
                shopOrderDto.setShopGoodsDtos(shopGoodsDtos);
                shopOrderDto.setName(shopOrder.getName());
                shopOrderDto.setPhone(shopOrder.getPhone());
                shopOrderDto.setAddress(shopOrder.getAddress());
                shopOrderDto.setCtime(shopOrder.getCtime());
                shopOrderDto.setId(shopOrder.getId());
                shopOrderDto.setNote(shopOrder.getNote());
                shopOrderDto.setSendTime(shopOrder.getSendTime());
                shopOrderDto.setStatus(shopOrder.getStatus());
                shopOrderDto.setTotalPrice(shopOrder.getTotalPrice());
                shopOrderDto.setUserId(shopOrder.getUserId());
                shopOrderDto.setUtime(shopOrder.getUtime());
                shopOrderDto.setIsDiy(shopOrder.getIsDiy());
                shopOrderDto.setSign(shopOrder.getSign());
                shopOrderDto.setMsg(shopOrder.getMsg());
                shopOrderDtos.add(shopOrderDto);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("userName", userName);
        result.put("userId", userId);
        result.put("shopOrderDtos", shopOrderDtos);
        result.put("type", type);
        model.addAllAttributes(result);
        return new ModelAndView("android/myList");
    }

//    @RequestMapping(value = "/orderDetail")
//    @ResponseBody
//    public ModelAndView orderDetailPage(Model model,
//                                        @RequestParam("orderId") int orderId,
//                                        @RequestParam("userName") String userName,
//                                        @RequestParam("userId") int userId) {
//        Map<String, Object> result = new HashMap<>();
//        result.put("userName", userName);
//        result.put("userId", userId);
//        model.addAllAttributes(result);
//        return new ModelAndView("android/orderDetail");
//    }
//
//    @RequestMapping(value = "/group")
//    @ResponseBody
//    public ModelAndView groupPage(Model model,
//                                  @RequestParam("userId") int userId,
//                                  @RequestParam("userName") String userName) {
//        Map<String, Object> result = new HashMap<>();
//        result.put("userName", userName);
//        result.put("userId", userId);
//        model.addAllAttributes(result);
//        return new ModelAndView("android/group");
//    }
//
//    @RequestMapping(value = "/groupDetail")
//    @ResponseBody
//    public ModelAndView groupDetailPage(Model model,
//                                        @RequestParam("goodsId") int goodsId,
//                                        @RequestParam("userId") int userId,
//                                        @RequestParam("userName") String userName) {
//        Map<String, Object> result = new HashMap<>();
//        result.put("userName", userName);
//        result.put("userId", userId);
//        model.addAllAttributes(result);
//        return new ModelAndView("android/groupDetail");
//    }

    private List<ShopGoodsDto> getShopGoodsDtos(List<ShopGoods> goodsList, List<ShopType> shopTypeList, List<ShopBrand> brandList) {

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
            shopGoodsDto.setPic(shopGoods.getPic());
            shopGoodsDto.setBuyTimes(shopGoods.getBuyTimes());
            shopGoodsDto.setType(shopGoods.getType());
            shopGoodsDto.setBrand(shopGoods.getBrand());
            shopGoodsDto.setTypeContent(typeMap.get(shopGoods.getType()));
            shopGoodsDto.setBrandContent(brandMap.get(shopGoods.getBrand()));
            shopGoodsDtos.add(shopGoodsDto);
        }
        return shopGoodsDtos;
    }


}
