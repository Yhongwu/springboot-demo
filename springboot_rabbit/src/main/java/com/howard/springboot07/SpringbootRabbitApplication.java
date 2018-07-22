package com.howard.springboot07;

import com.howard.springboot07.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class SpringbootRabbitApplication {
	@Autowired
	private Producer producer;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitApplication.class, args);
	}


	@GetMapping("ack")
	@ResponseBody
	public String ackTest() {
		for (int i = 0 ; i < 10; i ++ ) {
			producer.producer3("hello world : "+i);
		}
		/*producer.producerTopic1("hello,topic.aa");
		producer.producerTopic2("hello,topic.C");
		producer.producerTopic3("hello,aa.topic");*/
		return "OK";
	}
	@GetMapping("delay")
	@ResponseBody
	public String delayTest() {

		producer.producerDelay("hello world ");
		return "OK";
	}

	@GetMapping("plugins/delay")
	@ResponseBody
	public String delayPluginsTest() {
		producer.producerDelayPlugins("hello world ");
		return "OK";
	}
}
/**
 * springboot集成Rabbit
 * 启动程序后看到控制台输出：接收到的消息：Rabbit测试消息
 * 管理界面：http://192.168.124.128:15672 默认账户 guest/guest
 */


/**
 * 出现类似FatalListenerStartupException: Mismatched queues异常
 * 可能是新创建的队列与已有的冲突导致监听的队列不存在或出现其它问题
 * 用web界面先把相关队列删除
 * 再次启动
 *
 */