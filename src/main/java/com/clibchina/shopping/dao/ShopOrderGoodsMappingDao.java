package com.clibchina.shopping.dao;

import com.clibchina.shopping.domain.ShopOrderGoodsMapping;
import com.clibchina.shopping.domain.dto.GoodsSaleInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ShopOrderGoodsMappingDao {

    final String INSERT_KEYS = "order_id, goods_id, num, ctime, utime";
    final String INSERT_VALUES = "#{orderId}, #{goodsId}, #{num},#{ctime}, #{utime}";
    final String SELECT_KEYS = "id, " + INSERT_KEYS;

    @Insert("INSERT INTO shop_order_goods_mapping (" + INSERT_KEYS + ") VALUES " + "(" + INSERT_VALUES + ")")
    @Options(useGeneratedKeys = true)
    public int addShopOrderGoodsMapping(ShopOrderGoodsMapping shopOrderGoodsMapping);

    @Select("select " + SELECT_KEYS + " from shop_order_goods_mapping where id = #{id}")
    public ShopOrderGoodsMapping queryShopOrderGoodsMapping(int id);

    @Select("select " + SELECT_KEYS + " from shop_order_goods_mapping where order_id = #{orderId}")
    public List<ShopOrderGoodsMapping> queryShopOrderGoodsMappingByOrderId(@Param("orderId") int orderId);

    @Select("select * from shop_order_goods_mapping")
    public List<ShopOrderGoodsMapping> queryAllShopOrderGoodsMapping();

    @Update("update shop_order_goods_mapping set order_id=#{orderId}, goods_id = #{goodsId}, num=#{num}")
    public void updateShopOrderGoodsById(ShopOrderGoodsMapping shopOrderGoodsMapping);

    @Delete("delete from shop_order_goods_mapping where id = #{id}")
    public void deleteShopOrderGoodsMapping(int id);

    @Select("select goods_id as goodsId,sum(num) as saleNum from shop_order_goods_mapping where ctime > #{startTime} group by goods_id")
    public List<GoodsSaleInfo> countGoodsSaleInfo(@Param("startTime") int startTime);
}
