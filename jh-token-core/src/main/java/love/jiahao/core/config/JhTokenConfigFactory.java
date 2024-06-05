package love.jiahao.core.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import love.jiahao.core.error.JhErrorCode;
import love.jiahao.core.exception.JhTokenException;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <big>Jh-Token配置文件的构建工厂类</big>
 * <p>用于手动读取配置文件初始化 SaTokenConfig 对象，只有在非IOC环境下你才会用到此类</p>
 *
 * @author 13684
 * @data 2024/6/4 下午5:49
 */
public class JhTokenConfigFactory {
    private JhTokenConfigFactory() {
    }

    /**
     * 配置文件地址
     */
    public static String configPath = "jh-token.properties";

    /**
     * 根据 configPath 路径获取配置信息
     *
     * @return 生成配置对象
     */
    public static JhTokenConfig createConfig() {
        return createConfig(configPath);
    }

    /**
     * 根据指定路径路径获取配置信息
     *
     * @param path 配置文件路径
     * @return 一个 SaTokenConfig 对象
     */
    public static JhTokenConfig createConfig(String path) {
        Map<String, String> map = readPropToMap(path);
        return (JhTokenConfig) initPropByMap(map, new JhTokenConfig());
    }

    /**
     * 根据映射表初始化对象属性。
     * 该方法通过反射机制，根据提供的映射表（Map）为指定对象的字段赋值。如果映射表为空，则创建一个新的HashMap。
     * 支持直接对类进行初始化，此时不会对特定实例进行赋值。
     *
     * @param map 映射表，键为字段名，值为字段的对应值。
     * @param obj 要初始化属性的对象，或类的Class对象。
     * @return 无返回值。
     * @throws JhTokenException 如果属性赋值过程中出现异常，则抛出此异常。
     */
    private static Object initPropByMap(Map<String, String> map, Object obj) {
        // 检查映射表是否为空，如果为空则初始化一个新的HashMap。
        if (MapUtil.isEmpty(map)) {
            map = new HashMap<>();
        }

        // 获取类信息，如果obj是Class对象，则处理类信息，否则处理对象实例的类信息。
        // 1.取出类型
        Class<?> cs;
        if (obj instanceof Class) {
            cs = (Class<?>) obj;
            // 如果是Class对象，则不需要对实例进行赋值。
            obj = null;
        } else {
            // 获取对象的类信息。
            cs = obj.getClass();
        }

        // 遍历类的所有声明字段，尝试从映射表中获取对应字段的值并赋值给字段。
        // 2.遍历类型属性，反射复制
        for (Field field : cs.getDeclaredFields()) {
            // 从映射表中获取字段名对应的值。
            String value = map.get(field.getName());
            // 如果字段值为空，则跳过当前字段。
            if (StrUtil.isEmpty(value)) {
                continue;
            }
            try {
                // 尝试将映射表中的值转换为字段的类型。
                Object convertValue = Convert.convert(field.getType(), value);
                // 设置字段可访问，以跨过访问权限限制。
                field.setAccessible(true);
                // 为字段赋值。
                field.set(obj, convertValue);
            } catch (Exception e) {
                // 如果在赋值过程中出现异常，则抛出自定义异常，并附带错误信息和异常原因。
                throw new JhTokenException("属性赋值出错: " + field.getName(), e).setCode(JhErrorCode.CODE_10022);
            }
        }

        // 将处理完后的对象返回
        return obj;
    }



    /**
     * 将指定路径下的properties配置文件读取到Map中
     *
     * @param path 配置文件路径
     * @return 配置文件信息转化为的Map
     */
    private static Map<String, String> readPropToMap(String path) {
        // 初始化一个空的HashMap用于存储属性文件的键值对
        Map<String, String> map = new HashMap<>();
        try (
                InputStream inputStream = JhTokenConfigFactory.class.getClassLoader().getResourceAsStream(path)
        ) {
            // 获取属性文件的输入流
            // 如果输入流为空，则说明属性文件不存在，直接返回null
            if (ObjectUtil.isEmpty(inputStream)) {
                return null;
            }
            // 创建一个Properties对象用于加载属性文件
            Properties properties = new Properties();
            // 加载输入流中的属性文件内容
            properties.load(inputStream);
            // 遍历Properties对象中的所有键，将键值对添加到map中
            for (String key : properties.stringPropertyNames()) {
                map.put(key, properties.getProperty(key));
            }
        } catch (Exception e) {
            // 如果在加载属性文件的过程中发生异常，抛出自定义异常，并设置错误代码和原始异常
            throw new JhTokenException("配置文件读取失败: " + path, e).setCode(JhErrorCode.CODE_10021);
        }

        // 返回包含属性文件键值对的Map
        return map;
    }

}
