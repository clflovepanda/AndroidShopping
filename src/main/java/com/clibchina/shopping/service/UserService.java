package com.clibchina.shopping.service;

import com.clibchina.shopping.dao.ShopUserDao;
import com.clibchina.shopping.domain.ShopUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    ShopUserDao shopUserDao;

    public ShopUser queryShopUserByUserName(String userName) {
        ShopUser shopUser = shopUserDao.queryShopUserByUserName(userName);
        return shopUser;
    }

    public boolean checkLogin(String userName, String password) {
        ShopUser shopUser = shopUserDao.queryShopUserByUserName(userName);
        if (shopUser.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean register(ShopUser shopUser) {
        ShopUser temp = shopUserDao.queryShopUserByUserName(shopUser.getName());
        if (temp == null) {
            shopUserDao.addShopUser(shopUser);
            return true;
        } else  {
            return false;
        }

    }

    public void changePassword(ShopUser shopUser) {
        shopUserDao.updateShopUserById(shopUser);
    }
}
