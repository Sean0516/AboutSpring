1.   添加依赖
2. 配置yml 文件 ，主要是 kafka  的连接信息，以及序列化等信息
3. 创建消息生产类，通过KafkaTemplate类，send方法进行消息的发送（需要指定需要发生的小队队列的名称）
4.  创建消息消费类
使用 @KafkaListener(topics = {"topic"}, groupId = "groupid")、进行消息监听处理
