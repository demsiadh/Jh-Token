package love.jiahao.core.utils;

import cn.hutool.core.util.ObjectUtil;

/**
 * <big>包装后的hutool工具类</big>
 * <p>用来包装hutool并没有实现的方法来适配项目</p>
 *
 * @author 13684
 * @data 2024/6/4 下午4:21
 */
public class HuToolUtil {

    /**
     * 判断给定的对象是否为基本类型或字符串。
     *
     * @param obj 待判断的对象
     * @return 如果对象是基本类型或字符串，则返回true；否则返回false。
     */
    public static boolean isBasicType(Object obj) {
        // 判断对象是否为基本类型或字符串
        return ObjectUtil.isBasicType(obj) || obj instanceof String;
    }
}
