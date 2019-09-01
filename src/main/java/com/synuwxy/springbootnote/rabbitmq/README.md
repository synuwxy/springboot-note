## RabbitMQ

RabbitMQ是一种消息队列中间件,用于各组件或服务间的消息传递.


### 使用RabbitMQ
RabbitMQ的使用需要一个服务端做转发已达到各个客户端的消息通信
使用docker快速搭建RabbitMQ服务端
1. 下载带管理界面的rabbitmq服务端,并且对外暴露15672(管理员页面),5672端口
```
docker run -d --hostname my-rabbit -p 15672:15672 -p 5672:5672 --name rabbitmq -e RABBITMQ_DEFAULT_USER=username -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management
```
其他可配置参数详见官方在dockerhub上的说明 https://hub.docker.com/_/rabbitmq

2. 本地安装管理员页面地址 http://localhost:15672
3. 客户端连接5672端口即可

### 样例启动

1. 修改启动类SpringbootNoteApplication的注解范围
```
@ServletComponentScan("com.synuwxy.springbootnote.rabbitmq")
@ComponentScan("com.synuwxy.springbootnote.rabbitmq")
```
2. 在application.yml中配置启用 rabbitmq 配置
```yaml
spring:
  application:
    name: synuwxy-springboot-note
  profiles:
    active: rabbitmq # 指定当前使用哪个配置文件
```
3. 在application-rabbitmq.yml配置rabbitmq服务端地址
```yaml
# rabbitmq 配置
spring:
  rabbitmq:
    host:  # rabbitmq 服务端IP
    username:  # 用户名
    password:  # 密码
    port: 5672 # 端口 默认5672
```
4. 启动,访问接口在 ./controller 中
