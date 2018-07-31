package com.shadow.service;

import com.shadow.dto.Exposer;
import com.shadow.dto.SeckillExecution;
import com.shadow.entity.TSeckill;
import com.shadow.exception.RepeatKillException;
import com.shadow.exception.SeckillCloseException;
import com.shadow.exception.SeckillException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getAllSeckills() {
        List<TSeckill> result = seckillService.getAllSeckills();
        logger.info("list={}", result);
    }

    @Test
    public void getSeckillById() {
        TSeckill seckill = seckillService.getSeckillById(10001);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillUrl() {
        Exposer exposer = seckillService.exportSeckillUrl(10002);
        //b90da2818627d859dba954ad1fce4a4c
        logger.info("exposer={}", exposer);
    }

    @Test
    public void executeSeckill() {
        try {
            Thread.sleep(100);
            SeckillExecution seckillExecution = seckillService.executeSeckill(10002, 15382290487L, "b90da2818627d859dba954ad1fce4a4c");
            logger.info("seckillExecution={}",seckillExecution);
        } catch (InterruptedException e) {
            logger.info(e.getMessage(), e);
        }

    }

    /**
     * 集成测试代码完整逻辑，注意可重复执行
     * @throws Exception
     */
    @Test
    public void testSeckillLogic() throws Exception{
        long id = 10003;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone = 15382290609L;
            String md5 = exposer.getMd5();
            try{
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                logger.info("seckillExecution={}",seckillExecution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }catch (SeckillException e){
                logger.error(e.getMessage());
            }
        }else {
            // 秒杀尚未开启
            logger.warn("exposer={}",exposer);
        }
    }
}