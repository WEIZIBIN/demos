package github.weizibin.classloader;

/**
 * 为了多次载入执行类而加入的加载器
 * 把 defineClass方法开放出来，只有外部显式调用的时候才回使用到loadByte方法
 * 由虚拟机调用时，仍然按照原来的双亲委派规则使用loadClass方法进行加载
 */
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadBytes(byte[] classBytes) {
        return defineClass(null, classBytes, 0, classBytes.length);
    }

}
