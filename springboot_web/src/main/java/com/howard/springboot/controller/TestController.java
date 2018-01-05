package com.howard.springboot.controller;

import com.github.pagehelper.Page;
import com.howard.springboot.domain.Test;
import com.howard.springboot.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TestService testService;

    @RequestMapping("find_by_name")
    public Test findByName() {
        return testService.findByName("howard");
    }

/*    @RequestMapping("test_cache")
    public List<Test> findAll() {
        long begin = System.currentTimeMillis();
        List<Test> list = testService.findAll();
        long ing = System.currentTimeMillis();
        testService.findAll();
        long end = System.currentTimeMillis();
        logger.debug("第一次请求时间：" + (ing - begin) + "ms");
        logger.debug("第二次请求时间:" + (end - ing) + "ms");
        return list;
    }*/

    @RequestMapping("save")
    public int saveUser() {
        Test u = new Test(12,"tom12",32);
        int result = testService.insert(u);
        return result;
    }

    @RequestMapping("find_by_page")
    public Page<Test> findByPage() {
        Page<Test> page = testService.findByPage(0, 2);
        return page;
    }

    /*********redis***************/
    @RequestMapping("save_redis")
    public int setRedis() {
        testService.save(new Test(2,"tom2",22));
        return 1;
    }

    @RequestMapping("get_redis")
    public Test getRedis() {
        return testService.get(2+"");
    }

    /**
     * 查询某个test
     * 如果redis存在就直接取出
     * 不存在从数据库进行查询
     * @return
     */
    @RequestMapping("get_by_redis")
    public Test getByRedis() {
        return testService.findById(3);
    }
}
