spring 对于上下文的bean  ，当自动装配时，如果bean 的名称相同，spring 无法做出选择 。这就所谓的bean 自动装配的歧义性。所以，当发现歧义性的时候，需要通过一些的方案来解决这个问题。 将可选bean 中的某个设置为首选（primary）的bean  或者使用限定符（qualifier） 来帮助spring选择需要的bean 

### 标识首选的bean (@Primary)
在声明bean 的时候，通过将其中一个可选的bean 设置为首选的bean 能够避免自动装配时的歧义性。当遇到歧义性的时候，spring会使用首选的bean 

```
@Bean  
@Primary 
```
### 限定自动装配的bean  @Qualifier 
设置首选bean 的局限性在于@Primary 无法将可选方案的访问限定到唯一一个无歧义性的选项中，只能标识一个优选的选择方法，当首选bean的数量超过一个时，就没有其他的方法进一步缩小可选范围 使用限定符可以将所有的可选的bean 缩小范围，达到只有一个bean 满足所规定的限制条件

@Qualifier  注解是使用限定符的主要方式，可以和@Autowired协同使用，在注入的时候指定想要注入的bean  


```
@Qualifier(beanName)
@Autowired
```
