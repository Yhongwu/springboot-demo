package com.howard.springboot07.config;

import com.howard.springboot07.consumer.test3.MyConsumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现CommandLineRunner指定程序启动要运行的代码 通过重写run实现
 */
@Configuration
public class RabbitConfig /*implements CommandLineRunner*/{

/*    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;*/
    /**
     * 声明队列hello
     * 对应测试1
     * @return
     */
    @Bean
    public Queue Queue() {
        // Queue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        // 默认 name, true, false, false,null
        // return new Queue("hello");

        return new Queue("queue1",false,false,true,null);
    }


   /* public ConnectionFactory connectionFactory() {

    }*/

/*   @Bean
   public ConnectionFactory connectionFactory() {
       CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host,port);
       cachingConnectionFactory.setUsername(username);
       cachingConnectionFactory.setPassword(password);
       cachingConnectionFactory.setVirtualHost("/");
       cachingConnectionFactory.setPublisherConfirms(true);
       return cachingConnectionFactory;
   }*/

    /**
     * springboot自动配置 如无特殊配置 该配置可省略 直接在需要的地方用@autowired注入
     * @param connectionFactory
     * @return
     */
    @Bean
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //回调 消息确认和失败回调
        //rabbitTemplate.setConfirmCallback(new MsgSendConfirmCallBack());
        //rabbitTemplate.setReturnCallback(new MsgSendReturnCallback());
        return rabbitTemplate;
    }

    /*********一对多测试******/
    @Bean
    public Queue Queue2() {
        return new Queue("queue2",true,false,false,null);
    }

    /*************消息确认*************/
    @Bean
    public Queue Queue3() {
        return new Queue("queue3",true,false,false,null);
    }


    @Autowired
    ConnectionFactory connectionFactory;
    /**
     * 消息确认配置
     * 单元测试不明显
     * 消息发送默认马上关闭导致单元测试部分消息未消费？？
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(Queue3()); //设置队列
        container.setExposeListenerChannel(true) ;
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);//消息确认后才能删除
       // container.setPrefetchCount(5);//每次处理5条消息
        container.setMessageListener(new MyConsumer()); //业务逻辑处理
        return container;
    }


    /**********************fanout测试 对应consumer/test4********************/
    /**
     * 三个队列
     * @return
     */
    @Bean
    public Queue queueFanout1() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue queueFanout2() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue queueFanout3() {
        return new Queue("fanout.C");
    }

    /**
     * fanout模式交换机
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 将三个队列与fanout交换机绑定
     * @param queueFanout1
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeA(Queue queueFanout1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanout1).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue queueFanout2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanout2).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue queueFanout3, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanout3).to(fanoutExchange);
    }

    /************direct模式 consumer/test5**********/
    @Bean
    public Queue queueDirect1() {
        return new Queue("directA");
    }
    @Bean
    public Queue queueDirect2() {
        return new Queue("directB");
    }
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    /**
     * 将direct交换机与队列绑定
     * 在交换机类型和bindingkey确定的情况下，routingkey决定了消息去向
     * @param queueDirect1
     * @param directExchange
     * @return
     */
    @Bean
    Binding bindingDirectExchange1(Queue queueDirect1,TopicExchange directExchange) {
        return BindingBuilder.bind(queueDirect1).to(directExchange).with("directA");
    }
    @Bean
    Binding bindingDirectExchange2(Queue queueDirect2, TopicExchange directExchange) {
        return BindingBuilder.bind(queueDirect2).to(directExchange).with("directB");
    }

/*****************topic模式测试：consumer/test6*****************/
    /**
     * 三个队列
     * @return
     */
    @Bean
    public Queue queueTopic1() {
        return new Queue("topic.A");
    }

    @Bean
    public Queue queueTopic2() {
        return new Queue("topic.B");
    }
    @Bean
    public Queue queueTopic3() {
        return new Queue("topic.C");
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 队列与topic交换机通过routingkey绑定
     * 大部分情况下routingkey和bindingkey被看作同一个东西
     * @param queueTopic1
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingTopicExchange1(Queue queueTopic1, TopicExchange exchange) {
        return BindingBuilder.bind(queueTopic1).to(exchange).with("topic.*");

    }
    @Bean
    Binding bindingTopicExchange2(Queue queueTopic2, TopicExchange exchange) {
        return BindingBuilder.bind(queueTopic2).to(exchange).with("*.topic");
    }
    @Bean
    Binding bindingTopicExchange3(Queue queueTopic3, TopicExchange exchange) {
        return BindingBuilder.bind(queueTopic3).to(exchange).with("topic.C");
    }

    /***************延迟队列****************/
    @Bean
    public Queue queueDelay1() {
        Map<String, Object> queueArgs = new HashMap<>();
        // 设置队列消息过期时间为15s
        queueArgs.put ("x-message-ttl" , 15000);
        queueArgs.put("x-dead-letter-exchange", "exchange-delay2");
        return new Queue("queueDelay1",false,false,false,queueArgs);
    }
    @Bean
    public Queue queueDelay2() {
        return new Queue("queueDelay2",false,false,false,null);
    }
    @Bean
    DirectExchange directDelayExchange() {
        return new DirectExchange("exchange-delay1");
    }
    @Bean
    FanoutExchange fanoutDelayExchange() {
        return new FanoutExchange("exchange-delay2");
    }
    @Bean
    Binding bindingDelayExchange1(Queue queueDelay1, DirectExchange directDelayExchange) {
        return BindingBuilder.bind(queueDelay1).to(directDelayExchange).with("routing_key");
    }
    @Bean
    Binding bindingDelayExchange2(Queue queueDelay2, FanoutExchange fanoutDelayExchange) {
        return BindingBuilder.bind(queueDelay2).to(fanoutDelayExchange);
    }

    /**********插件方式实现延迟队列***************/
    @Bean
    public Queue queueDelayPlugins() {
        return new Queue("queue-delay",false,false,false,null);
    }
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("exchange-delay", "x-delayed-message",true, false,args);
    }
    @Bean
    Binding bindingDelayPluginsExchange(Queue queueDelayPlugins, FanoutExchange fanoutDelayExchange) {
        return BindingBuilder.bind(queueDelayPlugins).to(delayExchange()).with("routing_key").noargs();
    }

    /**
     * springboot默认已经配置
     */
/*    @Autowired
    private RabbitTemplate rabbitTemplate;*/

    /**
     * 目的地队列
     * @return
     */
//    @Bean
//    public Queue queue() {
//        return new Queue("my-queue");
//    }

/*    @Override
    public void run(String... args) throws Exception {
        rabbitTemplate.convertAndSend("my-queue","Rabbit测试消息");
    }*/
}
