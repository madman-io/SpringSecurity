package com.example.demo.security.cors;

import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 设置跨域请求
 */
@Slf4j
@Configuration
@ConditionalOnExpression("'${park.cors.allow.enable}' == 'true'")
public class ParkCorsConfig {

    @Value("${park.cors.allow.urls:localhost}")
    private String corsAllowUrls;

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        if (StringUtils.isNotBlank(corsAllowUrls)) {
            List<String> corsAllowUrlList = Arrays.asList(corsAllowUrls.split(","));
            log.info("init cors allow urls : {}", JSON.toJSONString(corsAllowUrlList));
            if (CollectionUtils.isNotEmpty(corsAllowUrlList)) {
                for (String corsAllowUrl : corsAllowUrlList) {
                    //你允许跨域的域名，不允许设置为『*』，可以设置的pattern有: (具体域名不用任何通配符， *.test.com一级域名不能被统配)
                    config.addAllowedOrigin(corsAllowUrl);
                }
            }
        }


        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
