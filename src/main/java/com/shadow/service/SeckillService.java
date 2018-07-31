package com.shadow.service;

import com.shadow.dto.Exposer;
import com.shadow.dto.SeckillExecution;
import com.shadow.entity.TSeckill;
import com.shadow.exception.RepeatKillException;
import com.shadow.exception.SeckillCloseException;
import com.shadow.exception.SeckillException;

import java.util.List;

/**
 * 在设计业务接口的时候遵循：站在“使用者”角度设计接口，简单实用。
 * 四个方面：方法定义粒度、参数、返回类型、可能会抛出的异常信息
 */
public interface SeckillService {

    /**
     * 查询所有的秒杀记录
     *
     * @return
     */
    List<TSeckill> getAllSeckills();

    /**
     * 根据seckillId查询对应的秒杀记录
     *
     * @param seckillId
     * @return
     */
    TSeckill getSeckillById(long seckillId);

    /**
     * 秒杀开启时输出秒杀的接口地址
     * 否则输出系统时间和秒杀时间
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;
}
