package com.example.controller;

import com.example.common.CaptureConfig;
import com.wf.captcha.utils.CaptchaUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping
public class CaptureController {
    @RequestMapping("/captcha")
    public void captcha(@RequestParam String key,HttpServletRequest request,HttpServletResponse response) throws Exception{
        //png类型
        SpecCaptcha captcha=new SpecCaptcha(135,33,5);//len:验证码有几个字符
        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);//设置字符类别
        //验证码在后台保存一份，但不能保存在session里，存在redis、后台的某个map里
        CaptureConfig.CAPTURE_MAP.put(key,captcha.text().toLowerCase());
        CaptchaUtil.out(captcha,request,response);
    }
}
