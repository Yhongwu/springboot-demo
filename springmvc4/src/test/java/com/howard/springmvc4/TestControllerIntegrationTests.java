package com.howard.springmvc4;

import com.howard.springmvc4.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * springmvcd的测试
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})  //加载配置
@WebAppConfiguration("src/test/resources")   //声明加载的ApplicationContent是一个WebAppConfiguration,其属性默认指定web资源的目录是src/main/webapp,
                                            //这里重新指定为src/test/resources
public class TestControllerIntegrationTests {

    /**
     * 用于模拟mvc对象
     * 通过MockMvcBuilders.webAppContextSetup(this.wac).build();进行初始化
     */
    private MockMvc mockMvc;

    @Autowired
    private TestService testService;

    @Autowired
    private WebApplicationContext wac;
    /**
     * 可注入模拟的httpsession httprequest
     */
    @Autowired
    private MockHttpSession session;

    @Autowired
    private MockHttpServletRequest request;

    /**
     * 在测试前进行的初始化工作
     */
    @Before
    public void setup() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(this.wac).build();

    }

    /**
     * 模拟向/testpage进行get请求
     * @throws Exception
     */
    @Test
    public void testNormalController() throws Exception{
        mockMvc.perform(get("/testpage"))
                //预期返回状态200
                .andExpect(status().isOk())
                .andExpect(view().name("test"))
                //预期返回的页面名 test
                .andExpect(forwardedUrl("/WEB-INF/classes/views/test.jsp"))//11
                //预期转向的真实路径
                .andExpect(model().attribute("msg", testService.saySomething()));//12

    }

    @Test
    public void testRestController() throws Exception {
        mockMvc.perform(get("/testRest"))
                .andExpect(status().isOk())
                //预期返回的媒体类型
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(testService.saySomething()));
    }


}
