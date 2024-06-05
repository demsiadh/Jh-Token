package love.jiahao.core.config;

import java.io.Serializable;

/**
 * <big>配置类</big>
 * <p>可以根据yml，properties，Java代码的形式进行配置本类参数</p>
 *
 * @author 13684
 * @data 2024/6/4 下午5:22
 */
public class JhTokenConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 是否打印操作日志
     */
    private Boolean isLog = false;

    /**
     * 日志等级（debug、info、warn、error），此值与 logLevelInt 联动
     */
    private String logLevel = "debug";

    /**
     * 日志等级 int 值（1=debug、2=info、3=warn、4=error），此值与 logLevel 联动
     */
    private int logLevelInt = 1;

    /**
     * 是否启用动态activeTimeOut功能，如果不需要请设置为false，节省缓存请求次数
     */
    private Boolean dynamicActiveTimeout = false;

    /**
     * token 失效时间(单位秒) 默认1天， -1 代表永久
     */
    private long timeout = 60 * 60 * 24;

    /**
     * 是否在登录后将 token 写入到响应头
     */
    private Boolean isWriteHeader = false;

    /**
     * 是否打印彩色日志
     */
    private Boolean isColorLog = false;

    public Boolean getIsLog() {
        return isLog;
    }
    public JhTokenConfig setIsLog(Boolean isLog) {
        this.isLog = isLog;
        return this;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public JhTokenConfig setLogLevel(String logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public int getLogLevelInt() {
        return logLevelInt;
    }

    public JhTokenConfig setLogLevelInt(int logLevelInt) {
        this.logLevelInt = logLevelInt;
        return this;
    }

    public Boolean getColorLog() {
        return isColorLog;
    }

    public JhTokenConfig setColorLog(Boolean colorLog) {
        isColorLog = colorLog;
        return this;
    }

    /**
     * @return 是否启用动态 activeTimeout 功能，如不需要请设置为 false，节省缓存请求次数
     */
    public Boolean getDynamicActiveTimeout() {
        return dynamicActiveTimeout;
    }

    /**
     * @param dynamicActiveTimeout 是否启用动态 activeTimeout 功能，如不需要请设置为 false，节省缓存请求次数
     * @return 对象自身
     */
    public JhTokenConfig setDynamicActiveTimeout(Boolean dynamicActiveTimeout) {
        this.dynamicActiveTimeout = dynamicActiveTimeout;
        return this;
    }

    /**
     * @return token 有效期（单位：秒） 默认30天，-1 代表永久有效
     */
    public long getTimeout() {
        return timeout;
    }

    /**
     * @param timeout token 有效期（单位：秒） 默认30天，-1 代表永久有效
     * @return 对象自身
     */
    public JhTokenConfig setTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * @return 是否在登录后将 token 写入到响应头
     */
    public Boolean getIsWriteHeader() {
        return isWriteHeader;
    }

    /**
     * @param isWriteHeader 是否在登录后将 token 写入到响应头
     * @return 对象自身
     */
    public JhTokenConfig setIsWriteHeader(Boolean isWriteHeader) {
        this.isWriteHeader = isWriteHeader;
        return this;
    }
}
