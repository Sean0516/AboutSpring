spring boot 实现定时任务首先需要在配置类注解 @EnableScheduling 来开启定时任务的支持，然后在要执行定时任务的方法上注解 @Scheduled ，声明这是一个定时任务 

需要注意的是，定时任务使用的是同一个线程池，如果未配置线程池数量，那么所有的定时任务将由一个线程执行，如果某个定时任务存在阻塞的情况，那么其他的任务将无法执行。可以通过配置线程池数量解决该问题


```
/**
 * @author Sean
 * @description 定时任务线程池配置
 * @date 2020/4/28 21:58
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskScheduler());
    }

    @Bean
    protected ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(3);
        threadPoolTaskScheduler.setThreadNamePrefix("schedule-pool-thread-");
        threadPoolTaskScheduler.afterPropertiesSet();
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        return threadPoolTaskScheduler;
    }
}
```


```
@Component
public class SpringScheduledTaskService {
    @Scheduled (fixedRate = 2000)
    public void scheduleTaskOne(){
        System.out.println("每隔五秒执行一次的定时任务"+new Date());
    }
    @Scheduled(fixedDelay = 2000)
    public void scheduleTaskTwo(){
        System.out.println("上一次执行完毕时间点后1秒再次执行"+new Date());
    }
}

```
关于@scheduled 的参数有多种方式，可以根据自己的需求来进行选择。

@Scheduled(fixedRate=1000)：上一次开始执行时间点后1秒再次执行；

@Scheduled(fixedDelay=1000)：上一次执行完毕时间点后1秒再次执行；

@Scheduled(initialDelay=1000, fixedDelay=1000)：第一次延迟1秒执行，然后在上一次执行完毕时间点后1秒再次执行；
@Scheduled(cron=”* * * * ?”)：根据书写的cron规则执行。
关于cron 表达式
cron一共有7位，但是最后一位是年，可以留空，所以我们可以写6位：
* 第一位，表示秒，取值0-59
* 第二位，表示分，取值0-59
* 第三位，表示小时，取值0-23
* 第四位，日期天/日，取值1-31
* 第五位，日期月份，取值1-12
* 第六位，星期，取值1-7，星期一，星期二…，注：不是第1周，第二周的意思
* 另外：1表示星期天，2表示星期一。
* 第7为，年份，可以留空，取值1970-2099
* (*)星号：可以理解为每的意思，每秒，每分，每天，每月，每年…
* (?)问号：问号只能出现在日期和星期这两个位置，表示这个位置的值不确定，每天3点执行，所以第六位星期的位置，我们是不需要关注的，就是不确定的值。同时：日期和星期是两个相互排斥的元素，通过问号来表明不指定值。比如，1月10日，比如是星期1，如果在星期的位置是另指定星期二，就前后冲突矛盾了。
* (-)减号：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
* (,)逗号：表达一个列表值，如在星期字段中使用“1,2,4”，则表示星期一，星期二，星期四
* (/)斜杠：如：x/y，x是开始值，y是步长，比如在第一位（秒） 0/15就是，从0秒开始，每15秒，最后就是0，15，30，45，60 另：* /y，等同于0/y
* 0 0 3 * * ? 每天3点执行
* 0 5 3 * * ? 每天3点5分执行
* 0 5 3 ? * * 每天3点5分执行，与上面作用相同
* 0 5/10 3 * * ? 每天3点的 5分，15分，25分，35分，45分，55分这几个时间点执行

cron [在线生成表达式网址](http://cron.qqe2.com/)