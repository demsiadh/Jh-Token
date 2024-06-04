package love.jiahao.core.log;

import cn.hutool.core.util.StrUtil;
import love.jiahao.core.JhManager;
import love.jiahao.core.config.JhTokenConfig;

/**
 * <big>日志实现类【控制台打印】</big>
 * <p></p>
 *
 * @author 13684
 * @data 2024/6/4 下午5:18
 */
public class JhLogForConsole implements JhLog {
    /**
     * 日志等级
     */
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARN = 3;
    public static final int ERROR = 4;

    /**
     * 日志输出的前缀
     */
    public static String DEBUG_PREFIX = "JH [DEBUG]-->: ";
    public static String INFO_PREFIX = "JH [INFO] -->: ";
    public static String WARN_PREFIX = "JH [WARN] -->: ";
    public static String ERROR_PREFIX = "JH [ERROR]-->: ";

    /**
     * 日志输出的颜色
     */
    public static String DEBUG_COLOR = "\033[34m";
    public static String INFO_COLOR  = "\033[32m";
    public static String WARN_COLOR  = "\033[33m";
    public static String ERROR_COLOR = "\033[31m";

    @Override
    public void debug(String str, Object... args) {
        println(DEBUG, DEBUG_COLOR, DEBUG_PREFIX, str, args);
    }

    @Override
    public void info(String str, Object... args) {
        println(INFO, INFO_COLOR, INFO_PREFIX, str, args);
    }

    @Override
    public void warn(String str, Object... args) {
        println(WARN, WARN_COLOR, WARN_PREFIX, str, args);
    }

    @Override
    public void error(String str, Object... args) {
        println(ERROR, ERROR_COLOR, ERROR_PREFIX, str, args);
    }

    /**
     * 打印日志到控制台
     * @param level 日志等级
     * @param color 颜色编码
     * @param prefix 前缀
     * @param str 字符串
     * @param args 参数列表
     */
    public void println(int level, String color, String prefix, String str, Object... args) {
        // 获取全局配置
        JhTokenConfig config = JhManager.getConfig();
        // 必须要开启日志，并且当前日志等级大于等于配置的日志等级
        if (config.getIsLog() && level >= config.getLogLevelInt()) {
            // 是否开启了彩色日志
            if (config.getColorLog()) {
                System.out.println(color + prefix + StrUtil.format(str, args) + DEBUG_COLOR);
            }else {
                System.out.println(prefix + StrUtil.format(str, args));
            }
        }
    }
}
