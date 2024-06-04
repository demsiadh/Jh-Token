package love.jiahao.stp;

/**
 * <big>Token权限认证工具类</big>
 * <p></p>
 *
 * @author 13684
 * @data 2024/6/4 下午2:12
 */
public class StpUtil {
    /**
     * 多账号体系下的类型标识
     */
    public static final String TYPE = "login";
    /**
     * 底层使用的 StpLogic 对象
     */
    public static StpLogic stpLogic = new StpLogic(TYPE);

    public static void login(Object id) {
        stpLogic.login(id);
    }
}
