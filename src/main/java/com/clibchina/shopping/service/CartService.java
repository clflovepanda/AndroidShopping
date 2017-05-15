package com.clibchina.shopping.service;

import com.clibchina.shopping.dao.ShopCartDao;
import com.clibchina.shopping.domain.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘雯雯 on 17/4/18.
 */
@Service
public class CartService {

    @Autowired
    ShopCartDao shopCartDao;

    public List<ShopCart> getShopCartListByUserId(int userId) {
        List<ShopCart> shopCartList = shopCartDao.queryShopCartByUserId(userId);
        return shopCartList;
    }

    public ShopCart getShopCartByUserIdAndGoodsId(int userId, int goodsId) {
        ShopCart shopCart = shopCartDao.queryShopCartByUserIdAndGoodsId(userId, goodsId);
        return shopCart;
    }

    public void addShopCart(ShopCart shopCart) {
        shopCartDao.addShopCart(shopCart);
    }

    public void updateShopCart(ShopCart shopCart) {
        shopCartDao.updateShopCartByUserIdAndGoodsId(shopCart.getUserId(), shopCart.getGoodsId(), shopCart.getNum());
    }

    public void deleteShopCartByUserId(int userId) {
        shopCartDao.deleteShopCartByUserId(userId);
    }

    public void deleteShopCart(int userId, int goodsId) {
        shopCartDao.deleteShopCart(userId, goodsId);
    }

}
