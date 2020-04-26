spring batch 一款轻量的、全面的批处理框架，用于开发强大的日常运营的企业级批处理应用程序 是用来处理大量数据操作的一个框架，主要是用来读取大量数据，然后进行一定处理后输出成指定的形式

spring batch  主要由以下几个部分组成

### JobRepository  用来注册Job 的容器
JobRepository用于存储任务执行的状态信息，比如什么时间点执行了什么任务、任务执行结果如何等等。框架提供了2种实现，一种是通过Map形式保存在内存中，当Java程序重启后任务信息也就丢失了，并且在分布式下无法获取其他节点的任务执行情况；另一种是保存在数据库中，并且将数据保存在下面6张表里：

BATCH_JOB_INSTANCE 作业实例表，用于存放Job的实例信息
BATCH_JOB_EXECUTION_PARAMS 作业参数表，用于存放每个Job执行时候的参数信息，该参数实际对应Job实例的。
BATCH_JOB_EXECUTION 作业执行器表，用于存放当前作业的执行信息，比如创建时间，执行开始时间，执行结束时间，执行的那个Job实例，执行状态等。
BATCH_STEP_EXECUTION 作业步执行器表，用于存放每个Step执行器的信息，比如作业步开始执行时间，执行完成时间，执行状态，读写次数，跳过次数等信息。
BATCH_JOB_EXECUTION_CONTEXT 作业执行上下文表，用于存放作业执行器上下文的信息。
BATCH_STEP_EXECUTION_CONTEXT作业步执行上下文表，用于存放每个作业步上下文的信息。

### JobLauncher 用来启动job 的接口

### JoB  实际需要执行的任务 （一个job 可能包含多个step）
Step  包括  ItemReader  ItemProcessor  和ItemWriter

ItemReader   用来读取数据的接口 框架已经提供了多种ItemReader接口的实现类，包括对文本文件、XML文件、数据库、JMS消息等读的处理，当然我们也可以自己实现该接口

ItemProcessor   用来处理数据的接口process方法的形参传入I类型的对象，通过处理后返回O型的对象。开发者可以实现自己的业务代码来对数据进行处理

ItemWriter  用来输出数据的接口框架已经提供了多种ItemWriter接口的实现类，包括对文本文件、XML文件、数据库、JMS消息等写的处理，当然我们也可以自己实现该接口,spring batch 提供了大量的itemReader 的实现 ，来读取不同的数据来源

当然也可以使用手动的方式来触发任务(可以是定时任务，http调用等等)

1. 在properties 文件中，将batch 任务启动执行设置为false 
2. 在需要使用的run任务的地方， 注入 JobLauncher 和 Job  ，

```
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job importJob;
    使用  JobLauncher.run(job ,"任务需要的参数 jobParameters")
    @Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job importJob;
	@Test
	public void contextLoads() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
		System.out.println("test 进行batch 任务手动调用");
		JobParameters jobParameters=new JobParameters();
		jobLauncher.run(importJob,jobParameters);
		System.out.println("test 进行batch 任务手动调用结束");
	}
```




