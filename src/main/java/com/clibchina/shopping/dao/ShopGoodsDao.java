package com.clibchina.shopping.dao;

import com.clibchina.shopping.domain.ShopGoods;
import com.clibchina.shopping.domain.ShopType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ShopGoodsDao {

    final String INSERT_KEYS = "name, description, type, brand, origin_price, price, stock, pic, buy_times, sign";
    final String INSERT_VALUES = "#{name}, #{description}, #{type}, #{brand}, #{originPrice}, #{price}, #{stock}, #{pic}, #{buyTimes}, #{sign}";
    final String SELECT_KEYS = "id, " + INSERT_KEYS;

    @Insert("INSERT INTO shop_goods (" + INSERT_KEYS + ") VALUES " + "(" + INSERT_VALUES + ")")
    @Options(useGeneratedKeys = true)
    public int addShopGoods(ShopGoods shopGoods);

    @Select("select " + SELECT_KEYS + " from shop_goods where id = #{id}")
    public ShopGoods queryShopGoods(int id);

    @Select("select * from shop_goods")
    public List<ShopGoods> queryAllShopGoods();

    @Select("select * from shop_goods where name like concat(concat('%',#{searchMsg}),'%')")
    public List<ShopGoods> queryAllShopGoodsBysearchMsg(String searchMsg);

    @Select("select * from shop_goods where type = #{typeId}")
    public List<ShopGoods> queryShopGoodsesByTypeId(int typeId);

    @Update("update shop_goods set name=#{name}, description=#{description}, type=#{type}, brand=#{brand}, origin_price=#{originPrice}, price=#{price}, stock=#{stock}, pic=#{pic}, buy_times=#{buyTimes}, sign=#{sign} where id = #{id}")
    public void updateShopGoodsById(ShopGoods shopGoods);

    @Update("update shop_goods set stock = stock- #{num}, buy_times = buy_times+ #{num} where id = #{id}")
    public void reduceShopGoodsStockById(@Param("id") int id, @Param("num") int num);

    @Update("update shop_goods set sign=#{sign} where id = #{id}")
    public void updateShopGoodsSignById(int id, int sign);

    @Delete("delete from shop_goods where id = #{id}")
    public void deleteShopGoods(int id);
}
