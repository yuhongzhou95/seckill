package com.shadow.service.impl;

import com.shadow.dao.SeckillDao;
import com.shadow.dao.SuccessKilledDao;
import com.shadow.dto.Exposer;
import com.shadow.dto.SeckillExecution;
import com.shadow.entity.TSeckill;
import com.shadow.entity.TSuccessKilled;
import com.shadow.enums.SeckillStateEnum;
import com.shadow.exception.RepeatKillException;
import com.shadow.exception.SeckillCloseException;
import com.shadow.exception.SeckillException;
import com.shadow.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SeckillServiceImpl implements SeckillService {

    private static final Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    private final String slat = "asdfghjkl";

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    public List<TSeckill> getAllSeckills() {
        List<TSeckill> result = seckillDao.queryAll(0, 5);
        return result;
    }

    @Override
    public TSeckill getSeckillById(long seckillId) {
        TSeckill seckill = seckillDao.queryById(seckillId);
        return seckill;
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        TSeckill seckill = seckillDao.queryById(seckillId);
        // 查询结果失败
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        // 系统当前时间
        Date nowTime = new Date();
        // 秒杀尚未开始或者秒杀已经结束
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        // 暴露地址加密
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    /**
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {

        // 判断传入的md5是否和生成的md5一致
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        // 秒杀逻辑：减库存+插入成功秒杀记录
        try {
            Date nowTime = new Date();
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            // 减库存失败
            if (updateCount != 1) {

                throw new SeckillCloseException("seckill is closed");
            }
            int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
            // 重复秒杀
            if (insertCount != 1) {
                throw new RepeatKillException("seckill repeated");
            }
            TSuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
            return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (RepeatKillException e1) {
            logger.error(e1.getMessage());
            throw e1;
        } catch (SeckillException e2) {
            logger.error(e2.getMessage(), e2);
            throw new SeckillException("seckill inner error: " + e2.getMessage());
        }
    }
}
