package com.howard.spring4.fortest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hongwu on 2017/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class}) //加载applicationcontent的配置 classes：加载配置类
@ActiveProfiles("prod") //指明使用活动的环境
public class DemoBeanIntegrationTests {

    @Autowired
    private TestBean testBean;

    @Test
    public void prodBeanShouInject() {
        String actual = testBean.getContent();
        String expected = "prod";
        Assert.assertEquals(expected,actual);
    }
}
