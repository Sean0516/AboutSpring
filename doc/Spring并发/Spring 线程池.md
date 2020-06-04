spring 提供ThreadPoolTaskScheduler和ThreadPoolTaskExecutor 线程池，可以通过@Bean 注入自定义线程池

```
@Configuration
public class ThreadPoolConfig {
// 异步线程池
    @Bean("asyncExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //线程池核心线程数
        taskExecutor.setCorePoolSize(5);
        //线程池最大线程数
        taskExecutor.setMaxPoolSize(10);
        //缓冲队列
        taskExecutor.setQueueCapacity(10);
        //线程空闲后的最大存活时间
        taskExecutor.setKeepAliveSeconds(0);
        //线程名前缀
        taskExecutor.setThreadNamePrefix("custom-thread-");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(0);
        taskExecutor.setDaemon(true);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        //执行初始化
        taskExecutor.initialize();
        return taskExecutor;
    }
// 定时任务线程池
    @Bean("threadScheduler")
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        taskScheduler.setAwaitTerminationSeconds(0);
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        return taskScheduler;
    }
}
```

用户通过 @Autowired 注解进行装配
