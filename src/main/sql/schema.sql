-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE waes;

-- 使用数据库
use waes;

-- 创建检测任务表
CREATE TABLE task(
  task_id bigint NOT NULL AUTO_INCREMENT COMMENT '检测任务id',
  task_name varchar(120) NOT NULL COMMENT '检测任务名称',
  task_num int NOT NULL COMMENT '检测任务数量',
  start_time timestamp NOT NULL COMMENT '检测任务开始时间',
  end_time timestamp NOT NULL COMMENT '检测任务结束时间',
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY(task_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='检测任务表';

-- 初始化数据
insert into
  task(task_name,task_num,start_time,end_time)
values
  ('北京市残联',100,'2016-06-01 00:00:00','2016-06-02 00:00:00'),
  ('杭州市残联',200,'2016-06-01 00:00:00','2016-06-02 00:00:00'),
  ('上海市残联',300,'2016-06-01 00:00:00','2016-06-02 00:00:00'),
  ('武汉市残联',400,'2016-06-01 00:00:00','2016-06-02 00:00:00');

-- 检测成功明细表
-- 用户登录认证相关的信息
CREATE TABLE success_checked(
  task_id bigint NOT NULL COMMENT '检查任务id',
  user_phone bigint NOT NULL COMMENT '用户手机号',
  state tinyint NOT NULL DEFAULT -1 COMMENT '状态标识：-1无效 0成功 1通过 2不通过',
  create_time timestamp NOT NULL COMMENT '创建时间',
  PRIMARY KEY(task_id,user_phone),/*联合主键*/
  key idx_create_time(create_time)
)ENGINE InnoDB DEFAULT CHARSET=utf8 COMMENT='检测成功明细表';

-- 连接数据库控制台
mysql -u root -p