package love.jiahao.core.stp;

import cn.hutool.core.util.ObjectUtil;
import love.jiahao.core.JhManager;
import love.jiahao.core.error.JhErrorCode;
import love.jiahao.core.exception.JhTokenException;
import love.jiahao.core.exception.NotLoginException;
import love.jiahao.core.utils.HuToolUtil;

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
    }


}
