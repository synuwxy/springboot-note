## swagger2

swagger2 是一种生成RESTful风格接口文档的工具,主要用于后端生成RESTful风格接口文档.

自带UI接可以很清楚的展示调用接口的细节,方便前端或者其他服务调用.

### 样例启动

1. 启动类SpringbootNoteApplication上加上注解(默认有就无需添加)
```
@EnableSwagger2Doc
```
2. 启动

### swagger2使用方法

方法一:

springfox-swagger2 + springfox-swagger-ui的方式
缺点是
* 注解太多,代码写的很复杂,不利于观看
* 一定的学习成本(虽然不高)

方法二:

swagger-spring-boot-starter
拥有 方法一 所有功能的同时,简单使用可以做到代码无侵入

1. 导入依赖
```xml
<dependency>
	<groupId>com.spring4all</groupId>
	<artifactId>swagger-spring-boot-starter</artifactId>
	<version>1.9.0.RELEASE</version>
</dependency>
```

2. 启动类类名上添加注解
```
@EnableSwagger2Doc
```
3. 启动springboot(默认会为你所有的controller接口生成文档)
4. 默认UI界面 http://localhost:8080/swagger-ui.html

更多定制和文档:

> https://github.com/SpringForAll/spring-boot-starter-swagger