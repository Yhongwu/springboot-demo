package com.howard.springboot05.config;

import com.howard.springboot05.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * spring security配置
 * 需要继承WebSecurityConfigurerAdapter
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

//    @Autowired
//    DataSource dataSource;

    /**
     * 注册自定义获取用户方式的bean
     * @return
     */
    @Bean
    public SysUserService sysUserService() {
        return new SysUserService();
    }

    /**
     * 用户认证
     * 添加自定义的user detail认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //通用用户 自己指定实现方式
        auth.userDetailsService(sysUserService());
        //可在此配置内存中的用户
        //auth.inMemoryAuthentication().withUser("a").password("a").roles("admin");
        //直接指定jdbc用户 并使用自己的查询用户的sql和查询角色的sql覆盖默认提供的sql查询
        //auth.jdbcAuthentication().dataSource(DataSource).usersByUsernameQuery("select xxx").authoritiesByUsernameQuery("");
    }

    /**
     * 请求授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()  //所有请求需要登录后才可以访问
                .and()
                .formLogin()  //定制登录行为
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .permitAll()
                .and()
                //.rememberMe() //开启cookie
                //    .tokenValiditySeconds(1209600) //秒 cookie2个星期过期
                 //  .key("mykey")
                .logout().permitAll(); //定制注销行为
                /*.and() //权限认证
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ROLE_ADMIN") //匹配路径 拥有某角色才可以访问
                .antMatchers("/user/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")
                .anyRequest().authenticated(); //其余的都需要登录后才可访问*/
    }
}
