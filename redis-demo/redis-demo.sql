CREATE TABLE `redis-demo`.`KillGoods` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `stock` INT NULL,
  `in_stock` TINYINT(1) NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `redis-demo`.`KillGoodsRecord` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `goods_id` INT NULL,
  `customer_id` INT NULL,
  `kill_time` DATETIME NULL,
  PRIMARY KEY (`id`));
