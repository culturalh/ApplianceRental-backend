package com.jxau.li.aspect;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.jxau.li.common.context.LoginContext;
import com.jxau.li.mapper.OperationLogMapper;
import com.jxau.li.mapper.UserInfoMapper;
import com.jxau.li.model.OperationLog;
import com.jxau.li.model.User;
import com.jxau.li.model.resp.LoginResp;
import com.jxau.li.utils.JwtUtil;
import com.jxau.li.utils.SnowUtil;
import com.jxau.li.utils.TimeSimple;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final OperationLogMapper logMapper;
    private final HttpServletRequest request;

    @Autowired
    private final UserInfoMapper userInfoMapper;
    @Pointcut("@annotation(com.jxau.li.aspect.LogOperation)")
    public void logPointcut() {}

    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LogOperation annotation = signature.getMethod().getAnnotation(LogOperation.class);

        // 获取操作信息
        OperationLog log = buildOperationLog(annotation);

        try {
            Object result = joinPoint.proceed();
            // 异步保存成功日志
            CompletableFuture.runAsync(() -> logMapper.insert(log));
            return result;
        } catch (Throwable e) {
            // 记录失败日志（可扩展字段）
            log.setOperationType(log.getOperationType() + "_FAILED");
            CompletableFuture.runAsync(() -> logMapper.insert(log));
            throw e;
        }
    }

    private OperationLog buildOperationLog(LogOperation annotation) {
        // 从安全上下文中获取用户信息（示例）
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User userDetails = (User) authentication.getPrincipal();
        // 从请求头获取Token
        String tokenHeader = request.getHeader("Authorization");
        String token = null;
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            token = tokenHeader.substring(7);
        }
        JSONObject jsonObject = JwtUtil.getJSONObject(token);
        System.out.println(jsonObject.toString());
        Long id = jsonObject.getLong("id");
        User user = userInfoMapper.selectById(id);
        String role = "";
        if("0".equals(user.getRole())){
            role = "管理员";
        }else if("1".equals(user.getRole())){
            role = "普通用户";
        }else{
            role = "商家";
        }
        String time = TimeSimple.getTime();
        String name = user.getName();
        OperationLog operationLog = new OperationLog();
        //此处需要登录之后为从上下文中获取用户信息
        operationLog.setLogId(SnowUtil.getSnowflakeNextId());
        operationLog.setOperationType(annotation.type());

        operationLog.setOperationTime(time);
        operationLog.setRole(role);
        operationLog.setUsername(name);
        return operationLog;
    }
}