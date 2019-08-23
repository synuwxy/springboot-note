## mybatis

比较流行的ORM框架

### 样例启动

1. 修改启动类SpringbootNoteApplication的注解范围
```
@ServletComponentScan("com.synuwxy.springbootnote.mybatis")
@ComponentScan("com.synuwxy.springbootnote.mybatis")
```
2. 修改application.yml中 **spring.datasource** 下的数据库信息为自己的数据库信息
3. 导入resource/mybatis/sql/中的sql文件,检查数据库名称与application.yml配置是否一致
4. 启动

### mybatis使用方法

1. 添加依赖

```
<!-- mysql -->
<dependency>
    <groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>8.0.17</version>
</dependency>

<!-- mybatis -->
<dependency>
	<groupId>org.mybatis.spring.boot</groupId>
	<artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.0</version>
</dependency>

<!-- druid -->
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>druid-spring-boot-starter</artifactId>
	<version>1.1.17</version>
</dependency>
```
2. 添加application.yml配置

```yaml
spring:
  datasource:
    druid:
      url: # 数据库地址
      username: root
      password: #填写自己数据库的密码
    # druid 相关配置
      web-stat-filter:
        enabled: true # 启用StatFilter默认值
      stat-view-servlet:
        enabled: true # 打开druid内置页面
        login-username: root # 设置登录用户名
        login-password: 123456 # 设置登录密码
      aop-patterns: # 监控范围,aop方式
      filter:
        stat:
          enabled: true # 过滤器打开
          log-slow-sql: true # 是否监控sql
          db-type: mysql # 数据库类型

mybatis:
  type-aliases-package: # 实体类存放位置
  mapper-locations: # xml存放位置
```

3. 启动使用


## mybatis-generator

mybatis 自动生成实体类,sql和mapper的工具

### 使用方法

idea:
1. 添加依赖

```xml
<plugin>
    <groupId>org.mybatis.generator</groupId>
	<artifactId>mybatis-generator-maven-plugin</artifactId>
	<version>1.3.7</version>
	<configuration>
		<configurationFile>mybatis-generator配置文件地址</configurationFile>
		<verbose>true</verbose>
		<overwrite>true</overwrite>
	</configuration>
	<executions>
		<execution>
			<id>Generate MyBatis Artifacts</id>
			<goals>
				<goal>generate</goal>
			</goals>
		</execution>
	</executions>
	<dependencies>
		<dependency>
	    	<groupId>org.mybatis.generator</groupId>
	        <artifactId>mybatis-generator-core</artifactId>
	        <version>1.3.7</version>
	    </dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.17</version>
		</dependency>
	</dependencies>
</plugin>
```

2. 在指定位置编写generator配置文档
3. 找到idea侧栏的maven 插件列表,启动mybatis-generator插件
