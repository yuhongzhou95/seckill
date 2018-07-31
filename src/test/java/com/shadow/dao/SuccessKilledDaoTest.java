package com.shadow.dao;

import com.shadow.entity.TSuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    private static final Logger logger = LoggerFactory.getLogger(SuccessKilledDaoTest.class);
    
    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        int result = successKilledDao.insertSuccessKilled(10001, 123456789);
        logger.info(String.valueOf(result));
    }

    @Test
    public void queryByIdWithSeckill() {
        TSuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(10001L,123456789L);
        logger.info(successKilled.toString());
    }
}