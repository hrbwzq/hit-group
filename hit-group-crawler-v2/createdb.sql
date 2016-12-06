drop database if exists hit_crawler;
create database hit_crawler;
use hit_crawler;
create table t_log
(
  log_id bigint auto_increment,
  title_hash varchar(255) not null unique,
  create_time datetime not null,
  primary key (log_id)
);
