package net.renfei.aspects;

import lombok.extern.slf4j.Slf4j;
import net.renfei.annotation.OperationLog;
import net.renfei.sdk.utils.BeanUtils;
import net.renfei.sdk.utils.IpUtils;
import net.renfei.service.start.OperationLogService;
import net.renfei.service.start.OperationLogServiceFactory;
import net.renfei.service.start.dto.OperationLogDTO;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 在切面记录操作日志
 *
 * @author renfei
 */
@Slf4j
@Aspect
@Component
public class OperationAspect {
    private final OperationLogServiceFactory operationLogServiceFactory;

    public OperationAspect(OperationLogServiceFactory operationLogServiceFactory) {
        this.operationLogServiceFactory = operationLogServiceFactory;
    }

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(net.renfei.annotation.OperationLog)")
    public void operationLogPointCut() {
    }

    /**
     * 方法执行完以后切进去，记录日志
     *
     * @param joinPoint 切点
     * @param retObj    方法返回对象
     */
    @AfterReturning(value = "operationLogPointCut()", returning = "retObj")
    public void saveOperationLog(JoinPoint joinPoint, Object retObj) throws ClassNotFoundException, NotFoundException {
        Signature signature = joinPoint.getSignature();
        Method method = currentMethod(joinPoint, signature.getName());
        OperationLog annotation = method.getAnnotation(OperationLog.class);
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] params = joinPoint.getArgs();
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        Map<String, Object> nameAndArgs = getFieldsName(this.getClass(), clazzName, method.getName(), params);
        StringBuffer buffer = new StringBuffer();
        if (!CollectionUtils.isEmpty(nameAndArgs)) {
            for (Map.Entry<String, Object> stringObjectEntry : nameAndArgs.entrySet()) {
                String key = stringObjectEntry.getKey();
                String value = stringObjectEntry.getValue().toString();
                buffer.append(key).append("=");
                buffer.append(value).append("&");
            }
        }
        if (BeanUtils.isEmpty(buffer.toString())) {
            buffer.append(request.getQueryString());
        }
        OperationLogDTO operationLog = new OperationLogDTO();
        operationLog.setOperDate(new Date());
        operationLog.setOperType(annotation.operation().toString());
        operationLog.setOperModel(annotation.module().toString());
        operationLog.setOperIp(IpUtils.getIpAddress(request));
        operationLog.setOperDescribe(annotation.desc());
        operationLog.setClassName(className);
        operationLog.setMethodName(method.getName());
        operationLog.setParams(buffer.toString());
        if (retObj != null) {
            operationLog.setReturning(retObj.toString());
        }
        OperationLogService operationLogService = operationLogServiceFactory.getOperationLogService();
        operationLogService.log(operationLog, servletRequestAttributes);
    }

    /**
     * 获取当前执行的方法
     *
     * @param joinPoint  连接点
     * @param methodName 方法名称
     * @return 方法
     */
    private Method currentMethod(JoinPoint joinPoint, String methodName) {
        /**
         * 获取目标类的所有方法，找到当前要执行的方法
         */
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }

    private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args)
            throws NotFoundException {
        Map<String, Object> map = new ConcurrentHashMap<>();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);
        CtClass cc = pool.get(clazzName);
        CtMethod ctMethod = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = ctMethod.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attribute = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attribute == null) {
            return map;
        }
        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
        for (int i = 0; i < ctMethod.getParameterTypes().length; i++) {
            if (args[i] == null) {
                continue;
            }
            map.put(attribute.variableName(i + pos), args[i]);
        }
        return map;
    }
}
