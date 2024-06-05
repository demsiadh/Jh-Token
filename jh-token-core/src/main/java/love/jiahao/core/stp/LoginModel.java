package love.jiahao.core.stp;

import cn.hutool.core.util.ObjectUtil;
import love.jiahao.core.config.JhTokenConfig;

import java.util.Map;

/**
 * <big>指定登录模式</big>
 *
 * @author 13684
 * @data 2024/6/4 下午2:26
 */
public class LoginModel {
    /**
     * 扩展信息（只在jwt模式下生效）
     */
    public Map<String, Object> extraData;

    /**
     * 指定此次登录token最低活跃频率，单位秒（如果没有指定则使用全局配置）
     */
    private Long activeTimeout;

    /**
     * 指定此次登录token的最低活跃频率，单位：秒（没有指定就用全局配置）
     */
    public Long timeout;

    /**
     * 是否在登录后将Token写入到响应头
     */
    private Boolean isWriteHeader;

    /**
     * 获取扩展信息（只在jwt模式下生效）
     *
     * @return 扩展信息
     */
    public Map<String, Object> getExtraData() {
        return extraData;
    }

    /**
     * @return 此次登录 token 最低活跃频率，单位：秒（如未指定，则使用全局配置的 activeTimeout 值）
     */
    public Long getActiveTimeout() {
        return activeTimeout;
    }

    /**
     * @param activeTimeout 指定此次登录 token 最低活跃频率，单位：秒（如未指定，则使用全局配置的 activeTimeout 值）
     * @return 对象自身
     */
    public LoginModel setActiveTimeout(long activeTimeout) {
        this.activeTimeout = activeTimeout;
        return this;
    }

    /**
     * @return 是否在登录后将 Token 写入到响应头
     */
    public Boolean getIsWriteHeader() {
        return isWriteHeader;
    }

    /**
     * @param isWriteHeader 是否在登录后将 Token 写入到响应头
     * @return 对象自身
     */
    public LoginModel setIsWriteHeader(Boolean isWriteHeader) {
        this.isWriteHeader = isWriteHeader;
        return this;
    }

    /**
     * 构建对象，初始化默认值
     *
     * @param jhTokenConfig 配置
     */
    public LoginModel build(JhTokenConfig jhTokenConfig) {
        // 填充token失效时间
        if (ObjectUtil.isEmpty(this.timeout)) {
            this.timeout = jhTokenConfig.getTimeout();
        }
        // 填充token是否写入响应头
        if (ObjectUtil.isEmpty(this.isWriteHeader)) {
            this.isWriteHeader = jhTokenConfig.getIsWriteHeader();
        }
        return this;
    }
}
