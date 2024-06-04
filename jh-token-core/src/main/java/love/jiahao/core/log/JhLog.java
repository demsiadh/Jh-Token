package love.jiahao.core.log;

/**
 * <big>日志输出接口</big>
 * <p></p>
 *
 * @author 13684
 * @data 2024/6/4 下午5:18
 */
public interface JhLog {
    /**
     * 输出 debug 日志
     * @param str 日志内容
     * @param args 参数列表
     */
    void debug(String str, Object ...args);

    /**
     * 输出 info 日志
     * @param str 日志内容
     * @param args 参数列表
     */
    void info(String str, Object ...args);

    /**
     * 输出 warn 日志
     * @param str 日志内容
     * @param args 参数列表
     */
    void warn(String str, Object ...args);

    /**
     * 输出 error 日志
     * @param str 日志内容
     * @param args 参数列表
     */
    void error(String str, Object ...args);
}
