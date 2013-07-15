
CREATE TABLE `DB_CRAWLER`.`tb_crawler` {
   `id`                  INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
   `url`                 VARCHAR(500) NOT NULL COMMENT '信息 url',
   `md5_url`         VARCHAR(32) NOT NULL COMMENT 'URL MD5SUM',
   `site_name`      VARCHAR(50) NOT NULL COMMENT '抓取数据的站点名称，sina,wugu',

   `title`                VARCHAR(500) NOT NULL COMMENT '标题',
   `content`          TEXT NOT NULL COMMENT '内容',
   `source`            VARCHAR(50) NOT NULL COMMENT '信息来源',
   `author`            VARCHAR(50) NOT NULL DEFAULT '' COMMENT '信息作者',
   `tags`               VARCHAR(50) NOT NULL  DEFAULT '' COMMENT '标签，使用;分割',
 -- `imgs`              VARCHAR(50) NOT NULL  DEFAULT '' COMMENT '数据包含的图像地址，使用;分割',
   `publish_date`  TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00' COMMENT '信息发布时间',                                                       
   `type`               TINYINT(2) NOT NULL DEFAULT '0' COMMENT '信息类型，0-不进行分类；1-资讯；2-动态；3-科技；', 


   `create_date`    TIMESTAMP NOT NULL DEFAULT NOW(),
   PRIMARY KEY (`id`),
   UNIQUE KEY `md5_url` (`md5_url`),
   key `idx_type` (`type`)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT
    
    
