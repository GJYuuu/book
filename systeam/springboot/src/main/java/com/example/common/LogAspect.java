package com.example.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Admin;
import com.example.entity.Log;
import com.example.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAspect {
    @Resource
    private LogService logService;


    @Around("@annotation(autoLog)")
    public Object doAround(ProceedingJoinPoint joinPoint,AutoLog autoLog) throws Throwable{

        //操作内容
        String name= autoLog.value();
        //操作时间
        String time= DateUtil.now();
        //操作人
        String username="";
        Admin user=JwtTokenUtils.getCurrentUser();
        if(ObjectUtil.isNotEmpty(user)){
            username=user.getName();
        }
        //操作人IP
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//获得request请求
        String ip=request.getRemoteAddr();

        //执行具体的接口
        Result result=(Result)joinPoint.proceed();

        Object data=result.getData();
        if(data instanceof Admin){//返回的是否是admin对象，如果是，就是登录的接口
            Admin admin=(Admin) data;
            username=admin.getName();
        }

        Log log=new Log(null,name,time,username,ip);
        logService.add(log);
        return result;
    }
}
