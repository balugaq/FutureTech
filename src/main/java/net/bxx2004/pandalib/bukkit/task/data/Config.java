package net.bxx2004.pandalib.bukkit.task.data;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
/**
 * 判断注解
 */
public @interface Config {
    /**
     * 节点名称
     */
    String node();

    /**
     * 节点数据
     */
    String data();
}
