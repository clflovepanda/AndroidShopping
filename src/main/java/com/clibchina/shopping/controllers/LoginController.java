package com.clibchina.shopping.controllers;

import com.alibaba.fastjson.JSONObject;
import com.clibchina.shopping.domain.ShopUser;
import com.clibchina.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController extends PublicController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        boolean bool = userService.checkLoginC(userName, password);

        JSONObject jsonObject = new JSONObject();
        if (bool) {
            jsonObject.put("status", "ok");
        } else {
            jsonObject.put("status", "fail");
            jsonObject.put("msg", "用户名或密码错误");
        }
        writeAjax(response, jsonObject);
        return;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");

        JSONObject jsonObject = new JSONObject();
        if (password.equals(rePassword)) {
            ShopUser shopUser = new ShopUser();
            shopUser.setName(userName);
            shopUser.setEmail(email);
            shopUser.setPassword(password);
            boolean bool = userService.register(shopUser);
            if (bool) {
                jsonObject.put("status", "ok");
            } else {
                jsonObject.put("status", "fail");
                jsonObject.put("msg", "用户名重复");
            }
        } else {
            jsonObject.put("status", "fail");
            jsonObject.put("msg", "两次密码输入不一致");
        }
        writeAjax(response, jsonObject);
        return;
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");

        JSONObject jsonObject = new JSONObject();
        if (password.equals(rePassword)) {
            ShopUser shopUser = userService.queryShopUserByUserName(userName);
            if (email.equals(shopUser.getEmail())) {
                shopUser.setPassword(password);
                userService.changePassword(shopUser);
                jsonObject.put("status", "ok");
            } else {
                jsonObject.put("status", "fail");
                jsonObject.put("msg", "邮箱与预留邮箱不一致");
            }
        } else {
            jsonObject.put("status", "fail");
            jsonObject.put("msg", "两次密码输入不一致");
        }
        writeAjax(response, jsonObject);
        return;
    }

}
