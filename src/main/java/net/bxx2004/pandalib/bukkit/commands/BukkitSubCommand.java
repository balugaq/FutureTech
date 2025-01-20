package net.bxx2004.pandalib.bukkit.commands;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface BukkitSubCommand {
    /**
     * 主命令名
     */
    String mainCommand();

    /**
     * 用法
     */
    String usage();

    /**
     * 权限
     */
    String permission();

    /**
     * 描述
     */
    String description() default "";
}
