package test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.EnumMap;

public class KryoUtil {

    private static Kryo kryo;

    /**
     * 获得 Kryo 实例
     *
     * @return 当前 Kryo 实例
     */
    public static Kryo getInstance() {
        if (kryo == null) {
            kryo = new Kryo();
            // 支持对象循环引用（否则会栈溢出）
            kryo.setReferences(true); // 默认值就是 true，添加此行的目的是为了提醒维护者，不要改变这个配置

            // 不强制要求注册类（注册行为无法保证多个 JVM 内同一个类的注册编号相同；而且业务系统中大量的 Class 也难以一一注册）
            kryo.setRegistrationRequired(false); // 默认值就是 false，添加此行的目的是为了提醒维护者，不要改变这个配置

            // Skill
            kryo.register(EnumMap.class, new EnumMapSerializer());
        }
        return kryo;
    }

    /**
     * 序列化对象到文件里
     *
     * @param path 文件路径
     * @param o    要序列化的目标
     */
    public static void writeToFile(String path, Object o) {
        Output output = null;
        File file = new File(path);
        try {
            if (file.exists()) {
                file.delete();
            }
            output = new Output(new FileOutputStream(file));
            getInstance().writeObject(output, o);
            output.close();
        } catch (Throwable e) {
            e.printStackTrace();
            if (output != null) {
                output.close();
            }
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 从文件读取序列化对象
     *
     * @param path 文件路径
     */
    public static <T> T readFromFile(String path, Class<T> class1) {
        File file = new File(path);
        try (Input input = new Input(new FileInputStream(file))) {
            T o = getInstance().readObjectOrNull(input, class1);
            input.close();
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            FileUtils.deleteQuietly(file);
            return null;
        }
    }
}
