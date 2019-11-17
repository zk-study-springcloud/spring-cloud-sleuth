## Spring Cloud Sleuth 使用说明
### Zipkin-Server服务
从Spring Cloud 2.x开始不需要我们自行搭建zipkin服务端了，可以直接从官网下载。执行命令下载
```shell script
curl -SSL https://zipkin.io/quickstart.sh|bash -s
```
正常启动命令：
```shell script
java -jar zipkin.jar
```
引入RabbitMQ时（使用默认用户），启动命令为：
```shell script
java -jar zipkin.jar --zipkin.collector.rabbitmq.addresses=localhost
```
或（自定义用户）
```shell script
java -jar zipkin.jar --zipkin.collector.rabbitmq.addresses=localhost --zipkin.collector.rabbitmq.username=guest --zipkin.collector.rabbitmq.password=guest
```
启动顺利的情况下，查看RabbitMQ界面，会增加一条连接，队列中也增加一条，如图所示：
![image](https://github.com/zk-study-springcloud/spring-cloud-sleuth/blob/master/image/1572752547329.jpg)
### gateway服务和user服务
- 增加pom依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
</dependency>
```
- 增加yaml配置
```yaml
spring:
  rabbitmq:
    host: 192.168.31.124
    port: 5672
    username: admin
    password: admin
```
启动后访问接口，RabbitMQ上会显示一条消息，证明成功。
### 使用http传输链路，并存储在MySQL
1. 先注释gateway和userService服务的RabbitMQ依赖
2. 数据库创建zipkin数据库
3. 执行zipkin.sql脚本
 ```shell script
mysql -uroot -proot80 -Dzipkin < /usr/local/zipkin/zipkin.sql
```
4. 只需要在zipkin.jar启动时，增加使用MySQL条件即可
```shell script
java -jar zipkin.jar --MYSQL_HOST=192.168.31.124 --MYSQL_USER=root --MYSQL_PASS=root80 --STORAGE_TYPE=mysql
```
5. 注意防火墙端口是否开启