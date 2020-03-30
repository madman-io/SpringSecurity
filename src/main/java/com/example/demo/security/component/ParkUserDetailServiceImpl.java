package com.example.demo.security.component;

import com.example.demo.core.domain.CUser;
import com.example.demo.core.service.ICUserService;
import com.example.demo.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author chaofeng.zhang
 * @email wb689178@alibaba-inc.com
 * @date 2020/3/27
 */
@Component
public class ParkUserDetailServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ICUserService icUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户名：" + username);
        CUser cUser = icUserService.selectCUserByUserName(username);
        if (null == cUser && !StringUtils.isNotEmpty(cUser.getPassword())) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //User类是Spring内置的一个类，实现了UserDetails接口，而这个接口是UserDetailService的子接口
        return new User(username, cUser.getPassword(),
            true, true, true, true,
            AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}

