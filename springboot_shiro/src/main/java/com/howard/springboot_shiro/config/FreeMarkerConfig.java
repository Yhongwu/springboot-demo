package com.howard.springboot_shiro.config;

import freemarker.template.utility.XmlEscape;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
//测试暂通过
//@Configuration
public class FreeMarkerConfig {
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer () {
        FreeMarkerConfigExtend configExtend = new FreeMarkerConfigExtend();
        configExtend.setTemplateLoaderPath("/WEB-INF/ftl/");

        Map<String, Object> map = new HashMap<>();
        XmlEscape fmXmlEscape = new XmlEscape();
        map.put("xml_escape",fmXmlEscape);
        //map.put("api","api");
        configExtend.setFreemarkerVariables(map);

        configExtend.setDefaultEncoding("utf-8");

        Properties pro = new Properties();
        pro.setProperty("template_update_delay","0");
        pro.setProperty("defaultEncoding","UTF-8");
        pro.setProperty("url_escaping_charset","UTF-8");
        pro.setProperty("locale","zh_CN");
        pro.setProperty("boolean_format","true,false");
        pro.setProperty("datetime_format","yyyy-MM-dd HH:mm:ss");
        pro.setProperty("date_format","yyyy-MM-dd");
        pro.setProperty("time_format","HH:mm:ss");
        pro.setProperty("number_format","#");
        pro.setProperty("whitespace_stripping","true");
        pro.setProperty("auto_import","/common/config/top.ftl as _top,/common/config/left.ftl as _left,/common/config/html.ftl as _html,/common/config/bottom.ftl as _footer,/common/config/menu.ftl as _menu");
        configExtend.setFreemarkerSettings(pro);

        return configExtend;
    }
}
