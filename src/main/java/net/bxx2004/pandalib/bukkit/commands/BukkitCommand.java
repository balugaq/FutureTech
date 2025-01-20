package net.bxx2004.pandalib.bukkit.commands;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
/**
 * 命令注解
 */
public @interface BukkitCommand {
    /**
     * 命令名
     */
    String name();

    /**
     * 权限
     */
    String permission();

    /**
     * 别名
     */
    String[] aliases();

    /**
     * 权限消息
     */
    String permissionMessage() default "§cI''m sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.";
}
