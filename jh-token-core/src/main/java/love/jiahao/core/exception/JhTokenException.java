package love.jiahao.core.exception;

import love.jiahao.core.error.JhErrorCode;

/**
 * <big>框架异常类，内部发生错误时抛出，是其他异常的父类</big>
 * <p></p>
 *
 * @author 13684
 * @data 2024/6/4 下午3:18
 */
public class JhTokenException extends RuntimeException{
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 异常状态码
     */
    private int code = JhErrorCode.CODE_UNDEFINED;

    /**
     * 构造函数
     * @param code 异常状态码
     */
    public JhTokenException(int code) {
        super();
        this.code = code;
    }

    /**
     * 构造函数
     * @param code 异常状态码
     * @param message 异常信息
     */
    public JhTokenException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造函数：JhTokenException
     * 该构造函数用于创建一个带有特定错误消息的JhTokenException实例。
     * 通过传递错误消息，可以帮助调用者更好地理解为什么抛出了此异常。
     *
     * @param message 错误消息，详细描述了异常的原因或状况。
     */
    public JhTokenException(String message) {
        super(message);
    }

    /**
     * 构造函数：JhTokenException
     * 该构造函数用于创建一个带有详细消息和根本原因的JhTokenException实例。
     *
     * @param message 异常的详细信息，描述了异常发生情况。
     * @param cause 异常的根本原因，即导致当前异常的另一个异常实例。
     */
    public JhTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 设置异常代码，并返回当前异常实例。
     * 通过此方法，可以为异常对象设置特定的错误代码，以便更准确地表示异常的类型或状态。
     * 返回当前异常实例使得方法调用可以采用链式调用的方式，提高了代码的可读性和简洁性。
     *
     * @param code 异常的错误代码，用于标识异常的类型或状态。
     * @return 返回当前的异常实例，支持链式调用。
     */
    public JhTokenException setCode(int code) {
        this.code = code;
        return this;
    }
}
