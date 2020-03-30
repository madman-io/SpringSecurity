package com.example.demo.security.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import com.aliyun.iotx.city.platform.common.util.IoTxResultUtils;
import com.aliyun.iotx.common.base.code.IoTxCodes;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author xinan.zf
 * @version 1.0.0
 * @date 2019/7/7 11:23 AM
 */
@Component
public class ParkLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Map<String,String> map=new HashMap<>(2);
        map.put("code", "200");
        map.put("msg", "登出成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(IoTxResultUtils.buildResult(IoTxCodes.SUCCESS, map)));
    }
}
