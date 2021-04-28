[English](README.md) | [简体中文](README_zh_CN.md)

# start

[![Actions Status](https://github.com/renfei/start/workflows/build/badge.svg)](https://github.com/renfei/start/actions/workflows/build.yml)
[![travis-ci](https://api.travis-ci.com/renfei/start.svg?branch=master)](https://travis-ci.com/github/renfei/start)
![license](https://img.shields.io/github/license/renfei/start.svg)

With the accumulation of work experience, more and more projects in work and private projects need to be started, and
the repetitive content bores me, while maintaining many versions of the code. This project was born. It is not a
complete project but exists as a template project and a code baseline. In my future work and private projects, I will
use this project as the baseline version and add my own business code on top of it.

## Technology Selection

### Spring Boot

Spring , this is no need to explain, this reason is enough. This has nothing to do with Spring's IoC, DI, and AOP. The
trend in the industry is to use Spring. It must be Spring Boot. I don’t want to toss a bunch of XML configuration files.

### Mybatis

Foreigners like Spring Data JPA, and Chinese prefer Mybatis. JPA is very good, but the design idea of JPA is based on
domain-driven design. The premise is that your database table structure model is designed according to the specification
of domain-driven design, and then use ORM to map into objects, OOP programming, so that it is well isolated. The
difference between different databases. But what is the domestic situation? Everyone is programming for leaders, and the
needs are changing every day. There is no domain expert to design the model, the database structure is messy, and many
scenarios require us to write SQL to implement, so I chose Mybatis.

### Druid

Spring Boot 2.x uses HikariCP connection pool by default, but I chose Alibaba's Druid. HiKariCP is fast and has high
performance. It is biased towards performance. Druid is more inclined to monitoring and data access behavior
enhancement (WallFilter can prevent SQL injection, StatFilter can perform performance monitoring, LogFilter can output
SQL logs), and Alibaba has experience in Taobao's high concurrency big data, I believe in Druid's security and
stability.