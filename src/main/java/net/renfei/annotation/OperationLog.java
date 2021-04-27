package net.renfei.annotation;

import net.renfei.service.start.type.ModuleEnum;
import net.renfei.service.start.type.OperationTypeEnum;

import java.lang.annotation.*;

/**
 * 记录操作日志注解
 *
 * @author renfei
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {
    OperationTypeEnum operation() default OperationTypeEnum.RETRIEVE;

    ModuleEnum module() default ModuleEnum.UNKNOWN;

    String desc() default "";
}
