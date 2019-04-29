package com.base.demo.shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig.
 *
 * @author Dulk
 * @version 20190426
 * @date 2019/4/26
 */
@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager securityManager() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        DefaultSecurityManager defaultSecurityManager = (DefaultSecurityManager) securityManager;
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealms(defaultSecurityManager.getRealms());

        SecurityUtils.setSecurityManager(securityManager);
        return webSecurityManager;
    }



    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager); //important

        Ini ini = new Ini();
        ini.loadFromPath("classpath:shiro.ini");
        Map<String, String> map = new LinkedHashMap<>();

        ini.getSection("urls").entrySet().forEach(url -> {
            map.put(url.getKey(), url.getValue());
        });
        //过滤链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        shiroFilterFactoryBean.setLoginUrl("/login/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/login/unauthorized");
        return shiroFilterFactoryBean;
    }

}
