CREATE TABLE `redis-demo`.`kill_goods` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `stock` INT NULL,
  `in_stock` TINYINT(1) NULL,
  `start_kill_time` DATETIME NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `redis-demo`.`kill_goods_record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `goods_id` INT NULL,
  `customer_id` INT NULL,
  `kill_time` DATETIME NULL,
  PRIMARY KEY (`id`));
