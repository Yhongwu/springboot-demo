package com.howard.springboot07;

import com.howard.springboot07.producer.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("test");
	}

	@Autowired
	private Producer producer;

	/******一对一******/
	@Test
	public void test1() {
		producer.producer1("hello world");
	}

	/********一对多******/
	@Test
	public void test2() throws Exception{
		for (int i = 0 ; i < 10; i ++ ) {
			producer.producer2("hello world : "+i);
		}

		//TimeUnit.SECONDS.sleep(10L);
	}

	/**********消息确认测试*************/
	@Test
	public void test3() throws Exception{
		for (int i = 0 ; i < 10; i ++ ) {
			producer.producer3("hello world : "+i);
		}
	}
	/******** fanoutE测试**********/
	@Test
	public void test4() throws Exception{
		producer.producer4("hello world");
	}
	/***********direct测试********/
	@Test
	public void test5() {
		producer.producerDirect1("hello,directA");
		producer.producerDirect2("hello,directB");
	}

	/**********topic测试************/
	@Test
	public void test6() {
		producer.producerTopic1("hello,topic.aa");
		producer.producerTopic2("hello,topic.C");
		producer.producerTopic3("hello,aa.topic");
	}

	@Test
	public void test7() {
		producer.producerDelay("hello world");
	}
}
