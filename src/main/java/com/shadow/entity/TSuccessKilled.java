package com.shadow.entity;

import java.util.Date;

public class TSuccessKilled {

    private Long seckillId;

    private Long userPhone;

    private Byte state;

    private Date createTime;

    private TSeckill seckill;

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public TSeckill getSeckill() {
        return seckill;
    }

    public void setSeckill(TSeckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "TSuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", seckill=" + seckill +
                '}';
    }
}
