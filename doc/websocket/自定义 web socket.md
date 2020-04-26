webSocket 协议提供了通过一个套接字实现全双工通信的功能，他能够实现web 浏览器和服务器之间的异步通信，全双工意味着服务器可以发送消息给浏览器，浏览器也可以发送消息给服务器

Spring 为WebSocket 通信提供了以下的支持

1. 发送和接受消息的低层级API （自定义实现Socket 连接，断开，接受消息等具体实现）
2. 发送和接受消息的高级API
3. 用来发送消息的模板
4. 支持SocktJS  用来解决浏览器端，服务器以及代理不支持webSocket 的问题
 
实现自定义的Web Socket  API  主要是需要重写一个实现了WebSocketHandler 的类 ，当然，相比WebSocketHandler  ，更简单的方法是扩展 AbstractWebSocketHandler  可以用来处理多种消息类型handleBinaryMessage()  处理二进制消息handleTestMessage () 处理文本消息也可以扩展TextWebSocketHandler  类， 该类会拒绝处理二进制的消息，只处理文本消息 ，如果接受到二进制的消息。 将会关闭WebSocket 连接同理 ，也可以扩展BinaryWebSocketHandler  无法接受文本消息，接受文本消息，将会关闭连接

同时，可以根据afterConnectionEstablished 和afterConnectionClosed 以及handleTransportError 等方法，来记录连接信息。 当新建立连接的时候，会调用afterConnectionEstablished  方法，当关闭连接时 会调用 afterConnectionClosed  方法，来记录相应的消息。 

当实现了自定义的消息处理器之后，必须对其进行配置，这样spring 才能将消息转发给它。 在进行配置的时候，需要创建一个配置类，并在配置类上面使用@EanbleWebSocket  注解，并实现 WebSocketCongigurer  接口。重写registerWebSocketHandlers()方法是注册消息处理器的关键。通过重写该方法，我们得到了一个WebSocketHandlerRegistry对象，通过该对象可以调用addHandler()来注册信息处理器 ，以及指定对应的访问路径和访问权限

### 浏览器调用webSocket 
1. 创建websocket 实例 。通过实例打开指定的url  URL 使用 ws:// 前缀，如果是https 的webSocket ，前缀为  wss://   。 该webSocket 实例是浏览器自带的实例 。如果浏览器不支持 websocket  ，下面会介绍不支持的解决方法
2. 建立 webSocket 的事件处理功能。  onopen  onmessage onclose  onerror 等事件 ，这些事件和服务端 继承 WebSocketHandler  重写的功能是一一对应的

关于浏览器 或者服务器不支持websocket 的问题，使用sockJS 可以解决这个问题， SockJS 是  webSocket的一种模拟，在表面上它尽可能能对应WebSocketAPI,但是在底层它非常智能，如果webSocket 技术不可用的话，它就会选择另外的通信方式。 当然他会有限选用 webSocket，但是当webSocket不可用的话，他会选择其他的最优的可行方案而是实现也是非常简单的只需要在webSocket配置类重写registerWebSocketHandlers 方法上加上 withSockJS() 方法就可以了 ，当WebSocket 不可用的时候， SosckJS  的备用方案就可以发挥作用

### 自定义 web socket 实现流程
1. 配置 web socket config  （该方主要是通过实现 websocketconfigurer 来重registerWebSocketHandlers ，用来指定访问的url 和自定义的handler ）
2. 自定义handler 来实现客户端连接关闭错误，发送等方法，通过重写这些方法来实现自定义的业务逻辑

通过继承 TextWebSocketHandler来重写afterConnectionEstablished，afterConnectionClosed ，handleMessage，handleTransportError等方法，值得注意的是，重写的所有方法都是可以向client发送消息的   WebSocketSession  的sendMessage 可以发送各种类型的数据给前台



