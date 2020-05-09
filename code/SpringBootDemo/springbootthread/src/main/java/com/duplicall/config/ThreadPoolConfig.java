package com.duplicall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description TODO
 * @Author Sean
 * @Date 2020/5/9 15:46
 * @Version 1.0
 */
@Configuration
public class ThreadPoolConfig {
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
