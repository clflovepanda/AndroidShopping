package com.clibchina.shopping.controllers.android;

import com.alibaba.fastjson.JSONObject;
import com.clibchina.shopping.controllers.PublicController;
import com.clibchina.shopping.domain.ShopCart;
import com.clibchina.shopping.domain.ShopGoods;
import com.clibchina.shopping.domain.ShopOrder;
import com.clibchina.shopping.domain.ShopOrderGoodsMapping;
import com.clibchina.shopping.service.*;
import com.clibchina.shopping.tools.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/android")
public class AndroidOrderController extends PublicController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/confirmOrder", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView confirmOrder(@RequestParam("userName") String userName,
                                     @RequestParam("userId") int userId,
                                     @RequestParam("orderId") int orderId,
                                     @RequestParam("type") int type) throws IOException {

        ShopOrder shopOrder = orderService.getShopOrder(orderId);
        shopOrder.setStatus(2);
        orderService.updateShopOrder(shopOrder);

        return new ModelAndView("redirect:/android/myList?userName=" + userName + "&userId=" + userId + "&type=" + type);
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView cancelOrder(@RequestParam("userName") String userName,
                                    @RequestParam("userId") int userId,
                                    @RequestParam("orderId") int orderId,
                                    @RequestParam("type") int type) throws IOException {

        ShopOrder shopOrder = orderService.getShopOrder(orderId);
        shopOrder.setStatus(3);
        orderService.updateShopOrder(shopOrder);

        return new ModelAndView("redirect:/android/myList?userName=" + userName + "&userId=" + userId + "&type=" + type);
    }

}
