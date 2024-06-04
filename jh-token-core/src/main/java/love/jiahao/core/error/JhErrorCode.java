package love.jiahao.core.error;

/**
 * <big>所有异常状态码常量</big>
 * <p></p>
 *
 * @author 13684
 * @data 2024/6/4 下午3:21
 */
public interface JhErrorCode {
    /**
     * 默认异常，表示未定义
     */
    int CODE_UNDEFINED = -1;
    /**
     * 登陆时账号id值为空
     */
    int CODE_11002 = 11002;
    /**
     * 配置文件为空
     */
    int CODE_10021 = 10021;
    /**
     * 配置文件属性复制给类失败
     */
    int CODE_10022 = 10022;
}
