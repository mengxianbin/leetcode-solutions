package singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderTest {

    Logger logger = LoggerFactory.getLogger(ClassLoaderTest.class);

    String classPath = System.getProperty("user.dir") + "/target/classes";
    String testClassPath = System.getProperty("user.dir") + "/target/test-classes";

    String objectClassName = "java.lang.Object";
    String singletonClassName = "singleton.SingletonClassLoader";

    ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

    private void test(String basePath, String className, boolean expected) throws ClassNotFoundException {
        test(basePath, className, expected, new SingletonClassLoader(basePath), systemClassLoader);
    }

    private void test(String basePath, String className, boolean expected, ClassLoader loader1, ClassLoader loader2) throws ClassNotFoundException {
        Class<?> c1 = load(basePath, className, loader1);
        Class<?> c2 = load(basePath, className, loader2);
        Assertions.assertEquals(expected, c1 == c2);
    }

    private Class<?> load(String basePath, String className, ClassLoader classLoader) throws ClassNotFoundException {
        Class<?> c = classLoader.loadClass(className);
        logger.info("{}: path={}, loader={}, class={}", className, basePath, classLoader.getClass().getName(), c == null ? null : c.hashCode());
        return c;
    }

    @Test
    public void testObject() throws ClassNotFoundException {
        test(classPath, objectClassName, true);
    }

    @Test
    public void testTestObject() throws ClassNotFoundException {
        test(testClassPath, objectClassName, true);
    }

    @Test
    public void testSingleton() throws ClassNotFoundException {
        test(classPath, singletonClassName, true);
    }

    @Test
    public void testTestSingleton() throws ClassNotFoundException {
        test(testClassPath, singletonClassName, false);
    }

    @Test
    public void testRepeatDifferentSingletonLoader() throws ClassNotFoundException {
        test(testClassPath, singletonClassName, false, new SingletonClassLoader(testClassPath), new SingletonClassLoader(testClassPath));
    }

    @Test
    public void testRepeatSameSingletonLoader() throws ClassNotFoundException {
        ClassLoader classLoader = new SingletonClassLoader(testClassPath);
        test(testClassPath, singletonClassName, true, classLoader, classLoader);
    }

    @Test
    public void testUrlClassLoader() throws MalformedURLException, ClassNotFoundException {
        URL[] urls = {new File(testClassPath).toURI().toURL()};
        test(testClassPath, singletonClassName, false, new URLClassLoader(urls, null), new URLClassLoader(urls, null));
    }

}
