spring 可以通过 @EnableAsync(配置到类上) 和@Async(配置到方法上) 注解来实现异步任务


```
@Service
public class AsyncTask {
    @Async
    public void sayHello(String name){
        System.out.println(Thread.currentThread().getName()+"___"+name);
    }
    @Async
    public void getName(String name){
        System.out.println(Thread.currentThread().getName()+"___"+name);
    }
}
```
同时可以通过实现AsyncConfigurer 来重写异步任务线程池配置

```
@Configuration
public class SyncPoolConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(100);
        //以下两项配置主要是针对程序停止后，线程池中线程也马上停止
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(0);
        threadPoolTaskExecutor.setThreadNamePrefix("sync-pool-thread-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
```
