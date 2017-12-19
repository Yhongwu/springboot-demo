package com.howard.spring4.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Created by hongwu on 2017/12/19.
 */
@Configuration
public class ConditionConifg {

    /**
     * 满足WindowsCondition中的条件则返回new WindowsListService()
     * @return
     */
    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowsList() {
        return new WindowsListService();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxList() {
        return new LinuxListService();
    }
}
