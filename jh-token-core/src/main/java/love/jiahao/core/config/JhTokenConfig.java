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
     * 是否打印彩色日志
     */
    private Boolean isColorLog = null;

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
}
