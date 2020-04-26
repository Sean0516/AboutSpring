ApplicationEvent以及Listener是Spring为我们提供的一个事件监听、订阅的实现，内部实现原理是观察者设计模式，设计初衷也是为了系统业务逻辑之间的解耦，提高可扩展性以及可维护性。事件发布者并不需要考虑谁去监听，监听具体的实现内容是什么，发布者的工作只是为了发布事件而已

ApplicationEvent 可以对多种事件进行监听，下面是一些简单的事件监听


ContextRefreshedEvent 事件需要特别注意，他可以支持在程序启动的时候执行需要执行的方法


```
public class ApplicationConfig implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextClosedEvent){
            //关闭事件
        }else if(event instanceof ContextRefreshedEvent){
            //刷新事件
              //一般来说，可以使用ContextRefreshedEvent 在程序启动的时候运行一些需要程序运行的方法，例如文本的读取等
            if (((ContextRefreshedEvent) applicationEvent).getApplicationContext().getParent() == null) {
                LOGGER.info("spring happen  refresh event ");
            }

        }else if(event instanceof ContextStartedEvent){
           // 开始事件
        }else if(event instanceof ContextStoppedEvent){
            // 停止事件
        }else{
            //其他事件
        }

    }
}
```
