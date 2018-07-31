-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE s_seckill;
-- 使用数据库
use s_seckill;
-- 创建秒杀库存表
CREATE TABLE `t_seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '商品库存数量',
  `start_time` date NOT NULL COMMENT '秒杀开启时间',
  `end_time` date NOT NULL COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';


-- 初始化数据
insert into t_seckill(name,number,start_time,end_time) values ('5000元秒杀iphone x',100,'2018-07-22 00:00:00','2018-07-23 00:00:00');
insert into t_seckill(name,number,start_time,end_time) values ('4000元秒杀iphone 8',200,'2018-07-22 00:00:00','2018-07-23 00:00:00');
insert into t_seckill(name,number,start_time,end_time) values ('3000元秒杀小米 mix2s',300,'2018-07-22 00:00:00','2018-07-23 00:00:00');
insert into t_seckill(name,number,start_time,end_time) values ('1元秒杀米家电动刷',5,'2018-07-22 00:00:00','2018-07-23 00:00:00');

-- 创建秒杀成功明细表
CREATE TABLE t_success_killed (
	seckill_id BIGINT NOT NULL COMMENT '秒杀商品id',
	user_phone BIGINT NOT NULL COMMENT '用户手机号',
	state TINYINT NOT NULL DEFAULT - 1 COMMENT '状态标识 无效:-1 成功:0 已付款:1',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (seckill_id, user_phone),
	/*联合主键*/
	KEY idx_create_time (create_time)
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT '秒杀成功明细表';
