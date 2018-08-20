package com.gavin.system;

import com.gavin.bean.Log;
import com.gavin.serivce.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Aspect
public class LogAction {
    /**
     * 返回通知(方法正常结束时执行的代码)
     * @param joinpoint 要切入的方法
     * @param result 方法的返回值
     */

    @Autowired
    private LogService logService;


    /**
     * 操作日志
     */
    public void afterReturnMethod(JoinPoint joinpoint, Object result){

        //获取方法入参list
        List<Object> args = Arrays.asList(joinpoint.getArgs());

        //获取方法名
        String methodName=joinpoint.getSignature().getName();

        //获取所在的类
        String className=joinpoint.getTarget().getClass().getName();

        Log log=new Log();
        log.setMethod(className+"->"+methodName);
        log.setInfo("ok");
        log.setCreateTime(new Date());

        //logService.edit(log);
    }


    /**
     * 异常日志
     * @param joinPoint 切入点
     * @param ex 异常
     */
    @AfterThrowing(value = "declearJoinPointExpression()",throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
        //获取方法名
        String methodName=joinPoint.getSignature().getName();

        //获取所在的类
        String className=joinPoint.getTarget().getClass().getName();

        Log log=new Log();
        log.setMethod(className+"->"+methodName);
        log.setInfo("异常:"+ex.toString());
        log.setCreateTime(new Date());
        logService.edit(log);
    }






    /**
     * 定义一个方法，用于声明切入点表达式，方法中一般不需要添加其他代码
     * 使用@Pointcut声明切入点表达式
     * 后面的通知直接使用方法名来引用当前的切点表达式
     */
    @Pointcut("execution(* com.gavin.controller.*.*(..))")
    private void declearJoinPointExpression(){}

}
