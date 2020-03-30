package com.example.demo.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyun.iotx.common.base.service.IoTxResult;
import com.example.demo.core.domain.CUser;
import com.example.demo.core.service.ICUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mc
 * @date 2020-03-27
 */
@Controller
@RequestMapping("/user")
public class CUserController {

    private static final Logger logger = LoggerFactory.getLogger(CUserController.class);

    private static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";

    @Autowired
    private ICUserService cUserService;

    @GetMapping("/getlist")
    @ResponseBody
    public IoTxResult<Object> getlist() {
        CUser cuser = new CUser();
        List<CUser> list = cUserService.selectCUserList(cuser);
        IoTxResult<Object> result = new IoTxResult();
        result.setData(list);
        return result;
    }

    @PostMapping("/login")
    @ResponseBody
    public void login(ModelMap model, HttpServletRequest httpServletRequest,HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        System.out.println("登陆成功");
    }
}
