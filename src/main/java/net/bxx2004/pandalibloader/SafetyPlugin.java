package net.bxx2004.pandalibloader;

/**
 * 需要验证的插件
 */
public interface SafetyPlugin {
    /**
     * 向服务器提交认证
     *
     * @return 验证消息
     */
    public SafetyPluginMessage verify();
}
