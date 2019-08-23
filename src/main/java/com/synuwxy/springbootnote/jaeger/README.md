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

### 问题

1. 对代码的侵入很大
2. 跨服务追踪不太好用

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

### 使用方法

1. 修改启动类SpringbootNoteApplication的注解范围
```
@ServletComponentScan("com.synuwxy.springbootnote.jaeger")
@ComponentScan("com.synuwxy.springbootnote.jaeger")
```
2. 在application.yml的 **jaeger 的配置** 中添加host为自己jaeger服务端的地址

3. 启动 