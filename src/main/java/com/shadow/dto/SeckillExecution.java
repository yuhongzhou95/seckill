package com.shadow.dto;

import com.shadow.entity.TSuccessKilled;
import com.shadow.enums.SeckillStateEnum;

import java.io.Serializable;

public class SeckillExecution implements Serializable {
    private long seckillId;
    // 秒杀执行结果状态
    private int state;
    // 秒杀执行结果状态标识
    private String stateInfo;
    // 秒杀成功对象
    private TSuccessKilled successKilled;

    // 成功返回信息
    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, TSuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    // 失败返回信息

    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public TSuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(TSuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
