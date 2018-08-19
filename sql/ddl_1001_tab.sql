drop table if exists ltd_co;

create table ltd_co (
id int primary key auto_increment comment 'ID',
name varchar(50) not null comment '公司名',
code varchar(10) not null comment '股票代码'
) comment '上市公司名录';