package com.howard.springboot_shiro.config;



import com.howard.springboot_shiro.shiro.AuthRealm;
import com.howard.springboot_shiro.shiro.MyCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;


@Configuration
public class ShiroConfig {
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager
                                              /*@Qualifier("roleAuthorizationFilter")RoleAuthorizationFilter roleAuthorizationFilter*/) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);

        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");

        //配置访问权限 使用LinkedHashMap保持顺序
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        //表示可以匿名访问
        filterChainDefinitionMap.put("/submit", "anon");//表示可以匿名访问
        filterChainDefinitionMap.put("/logout*","anon");
        filterChainDefinitionMap.put("/error","anon");
        filterChainDefinitionMap.put("/index.jsp*","authc");

        //表示需要认证才可以访问
        //filterChainDefinitionMap.put("/select", "authc,roles[customer,admin]");
        filterChainDefinitionMap.put("/*", "authc");
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/*.*", "authc");

        //配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/index", "user");
        filterChainDefinitionMap.put("/", "user");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //自定义过滤器的注入：roles authc等
        //Map<String,Filter> map = new HashMap<>();
        //map.put("roles",roleAuthorizationFilter);
        //bean.setFilters(map);

        return bean;
    }
    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        //注入自定义的realm
        manager.setRealm(authRealm);

        //注入缓存管理器;
        //manager.setCacheManager(ehCacheManager());

        //注入记住我管理器;
        manager.setRememberMeManager(rememberMeManager());

        return manager;
    }
    //配置自定义的权限登录器
    @Bean(name="authRealm")
    public AuthRealm authRealm() {
        AuthRealm authRealm=new AuthRealm();
        //设置需要的密码匹配方式
        authRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        //authRealm.setCredentialsMatcher(credentialsMatcher());
        return authRealm;
    }
    //配置自定义的密码比较器(凭证匹配器) 明文
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new MyCredentialsMatcher();
    }

    /**
     * 凭证匹配器 加密
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 注入自己实现的shiro filter
     * @return
     */
    //@Bean("roleAuthorizationFilter")
    // public RoleAuthorizationFilter roleAuthorizationFilter() {
    //     return new RoleAuthorizationFilter();
    //}

    /***********shiro注解方式*****************/
    /**
     * 设置代理方式
     * 可使用代替spring.aop.proxy-target-class=true
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        //允许代理类
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 开启shiro注解
     * @param manager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
    /************缓存*********************/
    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        System.out.println("ShiroConfiguration.getEhCacheManager()");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return cacheManager;
    }

    /******************记住我***************/
    /**
     * cookie对象
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }
}
