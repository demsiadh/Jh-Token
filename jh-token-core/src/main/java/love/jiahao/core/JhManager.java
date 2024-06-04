package love.jiahao.core;

import cn.hutool.core.util.ObjectUtil;
import love.jiahao.core.config.JhTokenConfig;
import love.jiahao.core.config.JhTokenConfigFactory;
import love.jiahao.core.log.JhLog;
import love.jiahao.core.log.JhLogForConsole;
import love.jiahao.core.stp.StpLogic;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <big>管理Jh-Token所有组件</big>
 * <p>可通过此类快速获取、写入各种全局组件对象</p>
 *
 * @author 13684
 * @data 2024/6/4 下午4:26
 */
public class JhManager {
    /**
     * 全局配置对象
     */
    public volatile static JhTokenConfig config;
    /**
     * 日志输出器
     */
    public volatile static JhLog log = new JhLogForConsole();
    /**
     * StpLogic 集合, 记录框架所有成功初始化的 StpLogic
     */
    public static Map<String, StpLogic> stpLogicMap = new LinkedHashMap<>();

    /**
     * 移除指定类型的StpLogic
     * @param loginType 登录类型
     */
    public static void removeStpLogic(String loginType) {
        stpLogicMap.remove(loginType);
    }

    /**
     * 向全局集合中put一个StpLogic
     * @param stpLogic StpLogic
     */
    public static void putStpLogic(StpLogic stpLogic) {
        stpLogicMap.put(stpLogic.getLoginType(), stpLogic);
    }

    public static JhTokenConfig getConfig() {
        if (ObjectUtil.isEmpty(config)) {
            synchronized (JhManager.class) {
                // 双重校验 防止多线程异常
                if (ObjectUtil.isEmpty(config)) {
                    // 初始化配置对象
                    setConfigMethod(JhTokenConfigFactory.createConfig());
                }
            }
        }
        return config;
    }

    private static void setConfigMethod(JhTokenConfig config) {
        JhManager.config = config;
    }
}
