### spring 实现Aspectj 的注解式切面编程
1. 使用@AspectJ 声明是一个切面
2. 使用@After @Before @Around 定义 advice ，可直接将拦截规则（切入点）作为参数
3. 为了使切点服用 可使用@pointcut 专门定义拦截规则，然后在@After @Before @Around 的参数中调用
4. 其中符合条件的每一个拦截处为来连接点（JoinPoint） 通过反射可获得注解上的属性，然后做日志记录相关的操
### @Aspect
用来声明切面的注解 ，是应用在类级别上的注解，此外，还应该使用@Component 注解来将该类注入spring 容器中

### @PointCut 

```
 @Pointcut("execution(public * com.voicecyber.controller.AdviceController..*(..))")
    public void advice(){
    }
```

通过使用该注解并提供一个方法声明，可以定义切入点。 该方法返回的类型为void ，而方法参数则应该与切点的参数匹配

### @Before 
通过该注解并根据给定的表达式，可以在实际方法中调用之前调用被注解通知的方法.此时，通知将拦截AdviceController 的公共方法，此外还可以从 joinPoint 获取访问的相关信息 --- 被通知的方法签名，被拦截的目标对象等。


```
 @Before("advice()")
    public void beforeAdvice(JoinPoint joinPoint){
    // 访问的方法
        String name = joinPoint.getSignature().getName();
    // 访问的参数
        Object[] args = joinPoint.getArgs();
        LOGGER.info("method name:"+name);
        LOGGER.info("参数："+args.toString());
        LOGGER.info("before enter controller"););
    }
```
### @After 
该注解可以在实际方法调用后调用被注解的通知方法， 此外还可以从 joinPoint 获取访问的相关信息 --- 被通知的方法签名，被拦截的目标对象等。

### @AfterReturning
该注解在接合点执行完毕后执行，如果被通知的方法中抛出一个异常，则不会执行该通知 ，同时，还可以访问接合点返回的值
### @AfterThrowing
该注解可以在异常抛出之后且再调用方法捕捉之前拦截该异常

```
@AfterThrowing(pointcut = "advice()",throwing = "t")
```
通过使用throwing  将异常作为一个Throwble 实例来访问，该参数名称应该与方法的参数名称像匹配 

### @Around 
该注解能够在目标方法执行之前和之后启动通知方法的执行，同时在方法中的参数为ProceedingJoinPoint  ，ProceedingJoinPoint 扩展了 JoinPoint 接口，并且只能在Around 类型的通知中使用


```
@Around("around()" )
    public Object aroundAdvice(ProceedingJoinPoint joinPoint){
        Object proceed=null;
        Method method = ((MethodSignature) 
        System.out.println("访问controller 的前置通知结束  ，进入后置通知");
        try {
         // 使用 joinPoint.proceed 进入后置通知 
            proceed = joinPoint.proceed(args);
        
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }
```


