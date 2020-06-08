### RestTemplate 访问HTTPS API 配置

```
@Bean
    public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(new TrustSelfSignedStrategy()).build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        ((HttpComponentsClientHttpRequestFactory) restTemplate
                .getRequestFactory()).setHttpClient(httpClient);
        return restTemplate;
    }
```
### RestTemplate 使用HttpClient  连接池发送消息 

```
@Configuration
public class RestClientConfig {
 @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(httpRequestFactory());
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }
 

@Bean
    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(5);
        connectionManager.setDefaultMaxPerRoute(5);

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(8000)
                .setConnectTimeout(8000)
                .setConnectionRequestTimeout(8000)
                .build();

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }

```
### 302 重定向处理 


```
HttpClient httpClient = HttpClientBuilder.create()
        .setRedirectStrategy(new LaxRedirectStrategy()) //未测试
        .build();
```
### 自定义异常 （可以通过自定义异常来重定向处理）
 通过继承 DefaultResponseErrorHandler  类 重写error 方法
    