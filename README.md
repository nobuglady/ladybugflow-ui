# LadybugFlow-UI

![](https://img.shields.io/badge/license-Apache2.0-yellow)
![](https://img.shields.io/badge/Java-1.8-orange)
![](https://img.shields.io/badge/SpringBoot-2.7.2-green)

LadybugFlow-UI is a job flow framework for java, <br />
It can run a jobflow design by json in java.<br />
Also we support a UI tool to draw a flow and convert to json file.

### 1. Setup

#### 1.1. DB

LadybugFlow-UI use mysql-db, you need to setup mysql and create a database.
and then run script file from [here](https://raw.githubusercontent.com/nobuglady/ladybugflow-ui/main/db/script.sql) to create tables.

#### 1.2. Configuration

You need to modify db settings in application.proerties below
```
spring.security.user.name=admin
spring.security.user.password=admin
spring.datasource.url=jdbc:mysql://localhost:3306/jobflow_new?useUnicode=true&characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
mybatis.configuration.map-underscore-to-camel-case=true
```

### 2. Run
Run project as spring boot application, and then access the url below:
```
http://localhost:8080/home
```
the default login user/pw is admin/admin

<img src="https://github.com/nobuglady/ladybugflow-ui/blob/main/readme/1.png?raw=true" alt="" width="400px"/>
