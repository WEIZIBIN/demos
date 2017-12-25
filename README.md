
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