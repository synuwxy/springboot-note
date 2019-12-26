## JPA

JPA是一种ORM框架,与h2数据库配合使用可以极速CRUD的开发

数据可以选择保存于内存中重启服务丢失重置,也可以保存在本地文件中,比较适合单机开发和demo开发

### h2对比mysql

h2不像mysql那样需要单独搭建,选择本地路径之后,数据可以随着项目跑,移植方便.

如果不是单机结构,使用起来就没有mysql好用.

### 样例启动

1. 修改启动类SpringbootNoteApplication的注解范围
```
@ServletComponentScan("com.synuwxy.springbootnote.jpa")
@ComponentScan("com.synuwxy.springbootnote.jpa")
```

2. 在application.yml中配置启用 jpa 配置
```yaml
spring:
  application:
    name: synuwxy-springboot-note
  profiles:
    active: jpa # 指定当前使用哪个配置文件
```

3.修改application.yml中 **spring.datasource** 下的数据库信息为自己的数据库信息
```yaml
# jpa + h2 配置
spring:
  datasource:
#    url: jdbc:h2:./data/db;DB_CLOSE_ON_EXIT=FALSE # 采用本地文件的方式
    url: jdbc:h2:mem:db # 采用内存方式
    username: root # 数据库用户名
    password: 123456 # 数据库密码
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: update
  h2:
    console:
      enabled: true # h2管理端访问
      path: /h2-console # 访问地址默认 http://localhost:8080/h2-console
```

4. 启动,访问接口在 ./controller 中