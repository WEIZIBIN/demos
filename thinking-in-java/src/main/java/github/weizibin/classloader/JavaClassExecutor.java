package github.weizibin.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;

/**
 * JavaCLass执行工具
 */
public class JavaClassExecutor {

    /**
     * 执行外部传过来的代表一个Java类的byte数组
     * 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info常量修改为劫持后的HackSystem类
     * 执行方法为该类的static main(String[] args)方法，输出结果为该类向System.out/err输出的信息
     */
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "github/weizibin/classloader/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadBytes(modiBytes);
        try {
            Method method = clazz.getMethod("main", new Class[] {String[].class});
            method.invoke(null, new String[]{null});
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return HackSystem.getBufferString();
    }

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = JavaClassExecutor.class.getClassLoader();
        System.out.println("Here is String come from Class 1 : ");
        File file1 = new File(classLoader.getResource("TestClassLoader1.class").getFile());
        JavaClassExecutor.execute(Files.readAllBytes(file1.toPath()));
        System.out.println("Here is String come from Class 2 : ");
        File file2 = new File(classLoader.getResource("TestClassLoader2.class").getFile());
        JavaClassExecutor.execute(Files.readAllBytes(file2.toPath()));
    }

}
