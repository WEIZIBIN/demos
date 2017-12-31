
# Demos - My Personal Learning

[![license](https://img.shields.io/github/license/mashape/apistatus.svg)]()

这是一个收集个人学习实例的仓库！

欢迎star、fork、use、Issue！

## dubbo-demo
[dubbo](http://dubbo.io/)是Alibaba开源的分布式服务框架，RPC调用框架

### dubbo-demo由三个模块组成
* dubbo-api(接口定义模块)
* dubbo-provider(服务提供者模块)
* dubbo-consumer(服务消费者模块)

### dubbo-api
该模块定义了服务的接口，使用或实现服务必须依赖于此模块

### dubbo-provider

[![dubbo](https://img.shields.io/badge/dubbo-v2.5.8-brightgreen.svg)](http://dubbo.io/)
[![jdk](https://img.shields.io/badge/jdk-v1.8-brightgreen.svg)]()

该模块实现了服务的接口，模块运行后向注册中心（本文使用zookeeper）注册服务。

### dubbo-consumer

[![dubbo](https://img.shields.io/badge/dubbo-v2.5.8-brightgreen.svg)](http://dubbo.io/)
[![zookeeper](https://img.shields.io/badge/zookeeper-v3.4.11-brightgreen.svg)](http://zookeeper.apache.org/)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-v1.5.9.RELEASE-brightgreen.svg)](https://projects.spring.io/spring-boot/)
[![jdk](https://img.shields.io/badge/jdk-v1.8-brightgreen.svg)]()

该模块使用SpringBoot搭建Web工程，Thymeleaf作为模板引擎，Controller中调用服务的接口。

## redis-demo
[redis](https://redis.io/)是开源的基于内存key-value存储系统，也是一个数据结构服务器

### redis-data types
* Binary-safe strings
* Lists
* Sets
* Sorted sets
* Hashes
* Bit arrays (or simply bitmaps)
* HyperLogLogs

### kill goods service

[![spring&springmvc](https://img.shields.io/badge/spring&springmvc-v4.3.7-brightgreen.svg)]()
[![mybatis](https://img.shields.io/badge/mybatis-v3.2.8-brightgreen.svg)]()
[![mysql](https://img.shields.io/badge/mysql-v3.2.8-brightgreen.svg)]()
[![jedis](https://img.shields.io/badge/jedis-v2.8.0-brightgreen.svg)]()
[![redis](https://img.shields.io/badge/redis-v4.0.6-brightgreen.svg)]()
[![jdk](https://img.shields.io/badge/jdk-v1.8-brightgreen.svg)]()

本文运用redis作为中间缓存开发了一个秒杀业务Demo。

秒杀场景具有以下特点：
* 高并发
* 请求数远超库存数
* 要求响应时效较高

对同一个商品进行读取更新库存，数据库中高并发单行更新响应慢，可承受并发量低，用户体验差，甚至可能产生死锁

数据库的读写成为了秒杀业务瓶颈

在本文中，运用redis完成秒杀业务中读写商品及库存操作，特有的原子性操作处理库存的减量，并定时持续化到数据库中

### 秒杀业务思路

* 添加商品：

商户添加秒杀商品->存入数据库->商品信息及库存存入redis缓存（或是秒杀前再存入缓存）

* 秒杀商品：

用户点击秒杀商品按钮->redis获取商品信息->判断是否已经到秒杀时间->redis获取库存->判断库存->秒杀->返回秒杀结果

* 定时持久化：

读取redis中所有库存->迭代更新到数据库中

Btw：其实秒杀业务优化还有很多学问，包括用户限流、异步处理等。

由于本Demo展示的重点在于redis的运用，其他的内容会再做Demo分享出来。