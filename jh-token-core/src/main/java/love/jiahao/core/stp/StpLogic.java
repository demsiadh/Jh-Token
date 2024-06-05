package love.jiahao.core.stp;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import love.jiahao.core.JhManager;
import love.jiahao.core.config.JhTokenConfig;
import love.jiahao.core.error.JhErrorCode;
import love.jiahao.core.exception.JhTokenException;
import love.jiahao.core.exception.NotLoginException;
import love.jiahao.core.utils.HuToolUtil;

import java.util.Map;

/**
 * <big>Token 权限认证，逻辑实现类</big>
 * <p>核心类，大部分功能都由此类提供具体逻辑实现</p>
 *
 * @author 13684
 * @data 2024/6/4 下午2:22
 */
public class StpLogic {
    /**
     * 账号类型标识，多账号体系时（一个系统多套用户表）用此值区分具体要校验的是哪套用户，比如：login、user、admin
     */
    public String loginType;
    /**
     * 当前对象配置
     */
    private JhTokenConfig config;

    /**
     * 获取当前StpLogic配置信息
     * @return 当前StpLogic配置信息
     */
    public JhTokenConfig getConfig() {
        return this.config;
    }

    /**
     * 设置当前StpLogic配置信息
     * @param config 当前StpLogic配置信息
     * @return 当前对象
     */
    public StpLogic setConfig(JhTokenConfig config) {
        this.config = config;
        return this;
    }

    public String getLoginType() {
        return this.loginType;
    }

    /**
     * 初始化
     * @param loginType 账号类型标识
     */
    public StpLogic(String loginType) {
        // 1.先清除此StpLogic对象在全局JhManager中的记录
        if (ObjectUtil.isNotEmpty(this.loginType)) {
            JhManager.removeStpLogic(this.loginType);
        }

        // 2.赋值
        this.loginType = loginType;

        // 3.将当前StpLogic对象在全局JhManager中注册
        JhManager.putStpLogic(this);
    }


    public void login(Object id) {
        login(id, new LoginModel());
    }

    public void login(Object id, LoginModel loginModel) {
        // 1.创建会话
        String token = createLoginSession(id, loginModel);
        // 2.在当前客户端注入token
        setTokenValue(token, loginModel);
    }

    public void setTokenValue(String token, LoginModel loginModel) {

    }

    public String createLoginSession(Object id, LoginModel loginModel) {
        // 1. 校验参数是否有效
        checkLoginArgs(id, loginModel);

        // 2.初始化loginModel，给一些参数补上默认值(也就说没有配置的使用全局配置)
        JhTokenConfig jhTokenConfig = getConfigOrGlobal();
        // 根据配置设置当前登录模式的登录配置
        loginModel.build(jhTokenConfig);

        // 3.给当前账号分配一个可用的token



        return null;
    }

    protected void checkLoginArgs(Object id, LoginModel loginModel) {
        // 1.账号id不能为空
        if (ObjectUtil.isEmpty(id)) {
            throw new JhTokenException("账号id不能为空").setCode(JhErrorCode.CODE_11002);
        }

        // 2.账号id不能是异常标记
        if(NotLoginException.ABNORMAL_LIST.contains(id.toString())) {
            throw new JhTokenException("loginId 不能为以下值：" + NotLoginException.ABNORMAL_LIST);
        }

        // 3.账号id不能是复杂类型
        if (!HuToolUtil.isBasicType(id)) {
            JhManager.log.warn("loginId 应该为简单类型，例如：String | int | long，不推荐使用复杂类型：" + id.getClass());
        }

        // 4.判断当前 StpLogic 是否支持extra扩展参数
        if (!isSupportExtra()) {
            Map<String, Object> extraData = loginModel.getExtraData();
            if (MapUtil.isNotEmpty(extraData)) {
                JhManager.log.warn("当前 StpLogic 不支持 extra 扩展参数模式，传入的 extra 参数将被忽略");
            }
        }

        // 5、如果全局配置未启动动态 activeTimeout 功能，但是此次登录却传入了 activeTimeout 参数，那么就打印警告信息
        if(!getConfigOrGlobal().getDynamicActiveTimeout() && ObjectUtil.isNotEmpty(loginModel.getActiveTimeout())) {
            JhManager.log.warn("当前全局配置未开启动态 activeTimeout 功能，传入的 activeTimeout 参数将被忽略");
        }
    }

    /**
     * 返回当前StpLogic使用的配置对象，如果没有配置，则使用全局配置
     * @return 当前StpLogic使用的配置对象
     */
    public JhTokenConfig getConfigOrGlobal() {
        JhTokenConfig cfg = this.getConfig();
        if (ObjectUtil.isNotEmpty(cfg)) {
            return cfg;
        }
        return JhManager.getConfig();
    }

    public boolean isSupportExtra() {
        return false;
    }


}
