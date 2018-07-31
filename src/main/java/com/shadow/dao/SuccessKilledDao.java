package com.shadow.dao;

import com.shadow.entity.TSuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {
    /**
     * 插入购买明细，可过滤重复插入
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实例
     *
     * @param seckillId
     * @return
     */
    TSuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
