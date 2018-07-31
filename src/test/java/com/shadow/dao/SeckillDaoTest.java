package com.shadow.dao;

import com.shadow.entity.TSeckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(SeckillDaoTest.class);
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void queryById() {
        TSeckill seckill = seckillDao.queryById(10000);
        logger.info(seckill.toString());
    }

    @Test
    public void queryAll() {
        //Parameter 'offset' not found. Available parameters are [1, 0, param1, param2]
        // 手动绑定参数
        List<TSeckill> list = seckillDao.queryAll(0, 10);
        for(TSeckill seckill : list){
            logger.info(seckill.toString());
        }
    }

    @Test
    public void reduceNumber() {
        int result = seckillDao.reduceNumber(10001L, new Date());
        logger.info(String.valueOf(result));
    }
}