spring 的事件是为bean与bean 之间的消息通信提供了支持，当一个bean 处理完一个任务后，希望另外一个bean知道并能够做出相应的处理，这时需要另外一个bean监听当前bean 所发送的事件。

spring 的自定义事件需要遵循以下流程

1. 自定义事件。 继承applicationEvent
2. 定义事件监听器 实现applicationListener ，并指定监听事件的类型 。使用onapplicationevent 方法对消息进行接收处理 
3. 使用容器发布事件  使用applicationcontext的publishevent 方法来发布事件
4. 发布事件