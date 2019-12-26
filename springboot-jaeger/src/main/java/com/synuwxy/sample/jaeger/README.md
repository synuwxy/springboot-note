## jaeger

jaeger是一种分布式跟踪系统,采用OpenTracing标准
jaeger的使用需要一个服务端
官网: https://www.jaegertracing.io/

docker 快速使用服务端(1.13版本):
``` shell script
docker run -d --name jaeger \
  -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 \
  -p 5775:5775/udp \
  -p 6831:6831/udp \
  -p 6832:6832/udp \
  -p 5778:5778 \
  -p 16686:16686 \
  -p 14268:14268 \
  -p 9411:9411 \
  jaegertracing/all-in-one:1.13
```
 
主要用于分布式系统中追踪服务间的接口调用关系,以及记录调用接口中的一些信息.

### 适用场景

1. 性能优化,可以对接口进行时间戳打表,接口性能问题一目了然,快速定位问题
2. 追踪不同服务间接口的调度关系(不太好用)
3. 支持异步

### 问题

1. 对代码的侵入很大
2. 跨服务追踪不太好用,很不愿意使用这种对代码入侵这么大的东西

### 总结 

在springboot项目中使用jaeger做性能优化:

可以考虑AOP/注解的方式进行打表,测试完毕之后将aop/注解删掉即可,基本不会对代码有太多的入侵.
aop的方式可以观察到所有方法之间的调度和运行时间(除了private方法),基本可以定位到某个类的某个方法上

private方法观测不到的解决方法:
1. 修改aop的代理方式为cglib
2. 使用代理类调用private方法
``` java
 AopContext.currentProxy() != null ? (类名)AopContext.currentProxy() : this;
```

支持异步链路追踪，使用inject + extract接口 

异步的追踪状态是 第一次发起的span root节点是一个全链路的状态，后面异步线程中extract的span会在ui页面上出现2次，一次是单独的状态，
一次是在 span root 的链路下面有记录。

所有后面的span生成都需要root节点的id，容器是一个map，key是写死的 "uber-trace-id"，取它的值就可以了

这个id在不同的线程中传递就可以实现异步的链路追踪

### 使用方法

1. 修改启动类SpringbootNoteApplication的注解范围
```
@ServletComponentScan("com.synuwxy.springbootnote.jaeger")
@ComponentScan("com.synuwxy.springbootnote.jaeger")
```

2. 在application.yml中配置启用 jaeger 配置
```yaml
spring:
  application:
    name: synuwxy-springboot-note
  profiles:
    active: jaeger # 指定当前使用哪个配置文件
```

2. 在application.yml的 **jaeger 的配置** 中添加host为自己jaeger服务端的地址
```yaml
# jaeger 配置
opentracing:
  jaeger:
    udp-sender:
      port: 6831
      host: localhost #这里填写jaeger服务端的ip
    log-spans: false
    aop:
      status: false
```

3. 启动,访问接口在 ./controller 中