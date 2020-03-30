package com.example.demo.security.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import com.aliyun.iotx.city.platform.common.util.IoTxResultUtils;
import com.aliyun.iotx.common.base.code.IoTxCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author xinan.zf
 * @version 1.0.0
 * @date 2019/7/7 10:35 AM
 */
@Component
public class ParkAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException,
        ServletException {
        logger.info("登录验证失败");
        Map<String,String> map=new HashMap<>(2);
        map.put("code", "10001");
        map.put("msg", exception.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(IoTxResultUtils.buildResult(IoTxCodes.REQUEST_ERROR, map)));
    }
}