package com.howard.spring4.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring的一个亮点就是bean可以不知道spring的存在
 * 但是有时候bean还是需要用到容器本身的功能资源，这时候就需要意识到spring的存在，这就是spring aware
 * Created by hongwu on 2017/12/19.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AwareConfig.class);
        AwareService bean = context.getBean(AwareService.class);
        bean.outputResult();

        context.close();
    }
}
