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
