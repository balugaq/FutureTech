package net.bxx2004.pandalib.bukkit.task.depend;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
/**
 * 依赖任务器注解
 */
public @interface Depend {
    /**
     * 插件名称
     */
    String name();

    /**
     * 插件版本
     */
    String version() default "all";

    /**
     * 是否异步
     */
    boolean asynchronous() default false;
}
