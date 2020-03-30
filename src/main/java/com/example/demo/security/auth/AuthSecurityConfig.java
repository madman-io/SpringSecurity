package com.example.demo.security.auth;

import com.example.demo.security.component.ParkAuthenticationFailureHandler;
import com.example.demo.security.component.ParkAuthenticationSuccessHandler;
import com.example.demo.security.component.ParkLogoutSuccessHandler;
import com.example.demo.security.component.ParkUserDetailServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${citylink.host}")
    private String cityLinkHost;

    @Value("${citylink.login.enabled}")
    private String cityLinkEnabled;

    /**
     * 依赖注入自定义的登录成功处理器
     */
    @Autowired
    ParkAuthenticationSuccessHandler parkAuthenticationSuccessHandler;

    /**
     * 依赖注入自定义的登录失败处理器
     */
    @Autowired
    ParkAuthenticationFailureHandler parkAuthenticationFailureHandler;

    /**
     * 依赖注入自定义的登出成功处理器
     */
    @Autowired
    ParkLogoutSuccessHandler parkLogoutSuccessHandler;

    /**
     * 依赖注入自定义的用户信息获取：用户名密码校验
     */
    @Autowired
    ParkUserDetailServiceImpl parkUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] authPermitRequests = {
            "/user/**",
            "/hello"
        };
        String[] csrfIgnoringRequests = {
            "/console/**",
            "/checkpreload.htm"
        };
        // 不校验用户
        http.authorizeRequests().antMatchers(authPermitRequests).permitAll();
        // csrf校验
        http.csrf().ignoringAntMatchers(csrfIgnoringRequests);

        if (StringUtils.equals(cityLinkEnabled, Boolean.TRUE.toString())) {
            http.authorizeRequests()
                .anyRequest()
                .authenticated();
            // 城市平台免登跳转
            http.formLogin().loginPage(cityLinkHost)
                .successHandler(parkAuthenticationSuccessHandler)
                .failureHandler(parkAuthenticationFailureHandler);
        } else {
            // 直接登录
            http.formLogin().loginPage("/index")
                .loginProcessingUrl("/console/login")
                .successHandler(parkAuthenticationSuccessHandler)
                .failureHandler(parkAuthenticationFailureHandler);
            http.authorizeRequests()
                .antMatchers("/index").permitAll()
                .antMatchers("/console/login").permitAll()
                .antMatchers("/checkpreload.htm").permitAll()
                .anyRequest()
                .authenticated();
            http.logout()
                .logoutUrl("/console/logout")
                .logoutSuccessUrl("/console/login")
                .logoutSuccessHandler(parkLogoutSuccessHandler)
                .invalidateHttpSession(true);
        }
    }

    /**
     * 配置用户密码盐值加密
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        if (StringUtils.equals(cityLinkEnabled, Boolean.FALSE.toString())) {
            // 直接登录验证
            auth.userDetailsService(parkUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
        }
    }
}
