package com.shadow.dao;

import com.shadow.entity.TSeckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SeckillDao {
    /**
     * 根据id查询秒杀对象
     *
     * @param seckillId
     * @return
     */
    TSeckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offet
     * @param limit
     * @return
     */
    List<TSeckill> queryAll(@Param("offset") int offet, @Param("limit") int limit);

    /**
     * 减库存
     *
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
}
