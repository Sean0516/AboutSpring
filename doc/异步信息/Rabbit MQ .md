### spring boot 使用 rabbit mq

1.   添加依赖
2. 配置yml 文件 ，主要是 rabbit MQ 的host port username password
3. 创建消息生产类 
注入 AmqpTemplate  通过该方法进行消息的发送（需要指定需要发生的小队队列的名称）
4.  创建消息消费类
使用@RabbitListener（queues:="消息队列的名称"） 注入类的头部
使用@RabbitHandler 注入在具体接受消息的方法上 ，接受对应的消息
5. 创建 rabbit config  配置类 主要是通过bean 注入 Queue  （消息队列）

