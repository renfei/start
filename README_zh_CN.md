[English](README.md) | [简体中文](README_zh_CN.md)

# start

![license](https://img.shields.io/github/license/renfei/start.svg)

随着工作经验的积累，在工作和私有项目中越来越多的项目需要开始启动，而重复的内容使我厌烦，同时维护很多版本的代码。
这个项目就产生了，它不是一个完整的项目而是作为模板项目、代码基线而存在，在我未来的工作和私有项目都将以此项目作为基线版本，在此之上增加各自的业务代码。

## 技术选型

### Spring Boot

不会 Spring 都不敢说自己是干 Java 的，这个无需解释，这个理由就够了。这跟 Spring 的 IoC、DI、AOP 无关，行业流行就是使用 Spring，你不用就是外行没得选。 必须得是 Spring Boot，我可不想折腾一堆
XML 配置文件，

### Mybatis

老外喜欢 Spring Data JPA，国人偏爱 Mybatis。 JPA很好，但是JPA的设计思想是基于领域驱动设计，前提是你的数据库表结构模型符合领域驱动设计的规范设计出来的，再使用 ORM
映射成对象，OOP编程，这样很好的隔离开了不同数据库的差异。 但是，国内情况是什么？大家都在面向领导编程，需求每天都在变，没有领域专家对模型进行设计，数据库结构乱七八糟，很多场景需要我们自己写 SQL 实现，所以我选择了 Mybatis。

### Druid

Spring Boot 2.x 默认使用 HikariCP 连接池，但我选择了阿里巴巴的 Druid。 HiKariCP 很快，性能很高，它偏向性能。Druid
则更偏向监控和数据访问行为增强（WallFilter可以防御SQL注入，StatFilter可以进行性能监视，LogFilter可以输出SQL日志），而且阿里巴巴有淘宝的高并发大数据的经验，我更相信 Druid 的安全和稳定性。