package love.jiahao.stp;

import lombok.AllArgsConstructor;

/**
 * <big>Token 权限认证，逻辑实现类</big>
 * <p>核心类，大部分功能都由此类提供具体逻辑实现</p>
 *
 * @author 13684
 * @data 2024/6/4 下午2:22
 */
@AllArgsConstructor
public class StpLogic {
    /**
     * 账号类型标识，多账号体系时（一个系统多套用户表）用此值区分具体要校验的是哪套用户，比如：login、user、admin
     */
    public String loginType;


    public void login(Object id) {
        login(id, new LoginModel());
    }

    public void login(Object id, LoginModel loginModel) {
        // 1.创建会话
        String token = createToken(id, loginModel);
        // 2.在当前客户端注入token
        setTokenValue(token, loginModel);
    }

    public void setTokenValue(String token, LoginModel loginModel) {

    }

    public String createToken(Object id, LoginModel loginModel) {
        return null;
    }
}
