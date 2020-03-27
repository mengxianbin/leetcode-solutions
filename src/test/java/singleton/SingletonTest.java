package singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class SingletonTest {

    private static Logger logger = LoggerFactory.getLogger(SingletonTest.class);

    @Test
    public void testNormal() {
        test(SingletonNormal.class, this::getInstance, new boolean[]{true, true, false, true, false});
    }

    @Test
    public void testInnerClass() {
        test(SingletonInner.class, this::getInstance, new boolean[]{true, true, false, true, false});
    }

    @Test
    public void testEnum() {
        test(SingletonEnum.class, this::getEnumInstance, new boolean[]{true, false, true, true, false});
    }

    private Object getEnumInstance(Class<?> klass) {
        return klass.getEnumConstants()[0];
    }

    private Object getInstance(Class<?> klass) {
        try {
            Method getter = klass.getDeclaredMethod("getInstance");
            return getter.invoke(null);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            logException(e);
        }

        return null;
    }

    private Object clone(Object o) {
        try {
            Method clone = Object.class.getDeclaredMethod("clone");
            clone.setAccessible(true);
            return clone.invoke(o);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logException(e);
        }

        return null;
    }

    private void test(Class<?> klass, Function<Class<?>, Object> getter, boolean[] expected) {
        List<Throwable> throwableList = new ArrayList<>();
        boolean[] result = {true};
        int[] index = {0};
        Consumer<Runnable> caller = runnable -> {
            boolean act = true;
            try {
                runnable.run();
            } catch (Throwable t) {
                throwableList.add(t);
                act = false;
            }
            boolean exp = expected[index[0]++];
            result[0] = result[0] && (act == exp);
            logger.info("Expected={}, Actual={}.", exp, act);
        };

        caller.accept(() -> testRegular(getter.apply(klass), getter));
        caller.accept(() -> testClone(getter.apply(klass)));
        caller.accept(() -> testReflect(getter.apply(klass)));
        caller.accept(() -> testDeserialize(getter.apply(klass)));
        caller.accept(() -> testClassLoader(getter.apply(klass), getter));

        throwableList.forEach(e -> logger.error("", e));
        Assertions.assertTrue(result[0]);
    }

    private void assertSingletonEquals(Object singleton1, Object singleton2, String msg) {
        assertCondition(singleton1, singleton2, msg, (o1, o2) -> o1 == o2);
    }

    private void assertCondition(Object singleton1, Object singleton2, String msg, BiFunction<Object, Object, Boolean> condition) {
        logger.info("Asserting {}: hash1={}, hash2={}.", msg, singleton1.hashCode(), singleton2.hashCode());
        Assertions.assertTrue(condition.apply(singleton1, singleton2), msg);
    }

    private void logTestName(String testName) {
        logger.warn("\nTest: {}.", testName);
    }

    private void logException(Exception e) {
        logger.warn("{}: -> {}.", e.getClass(), e.getMessage());
    }

    private void testRegular(Object singleton1, Function<Class<?>, Object> getter) {
        logTestName("regular");
        Object singleton2 = getter.apply(singleton1.getClass());
        assertSingletonEquals(singleton1, singleton2, "Regular");
    }

    private void testClone(Object singleton1) {
        logTestName("clone");
        Object singleton2 = clone(singleton1);
        assertSingletonEquals(singleton1, singleton2, "Clone");
    }

    private void testReflect(Object singleton1) {
        // newInstance
        try {
            logTestName("reflect - newInstance");
            Object singleton2 = singleton1.getClass().newInstance();
            assertSingletonEquals(singleton1, singleton2, "Reflect - newInstance");
        } catch (InstantiationException | IllegalAccessException e) {
            logException(e);
        }

        // Constructor
        try {
            logTestName("reflect - Constructor");
            Constructor<?> constructor = singleton1.getClass().getDeclaredConstructor();
            constructor.setAccessible(true);
            Object singleton2 = constructor.newInstance();
            assertSingletonEquals(singleton1, singleton2, "Reflect - constructor");
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            logException(e);
        }
    }

    private void testDeserialize(Object singleton1) {
        logTestName("deserialize");
        File file = new File("target/serialization");

        try {
            // Write object
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(singleton1);
            oos.flush();
            oos.close();
            fos.close();

            // Read object
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object singleton2 = ois.readObject();
            fis.close();
            ois.close();
            assertSingletonEquals(singleton1, singleton2, "Deserialize");
        } catch (IOException | ClassNotFoundException e) {
            logException(e);
        }
    }

    private void testClassLoader(Object singleton1, Function<Class<?>, Object> getter) {
        logTestName("class loader");

        try {
            String classPath = System.getProperty("user.dir") + "/target/test-classes";
            ClassLoader classLoader = new SingletonClassLoader(classPath);
            Class<?> singletonClass = classLoader.loadClass(singleton1.getClass().getName());
            assertCondition(singleton1.getClass(), singletonClass, "ClassLoader - loader", (o1, o2) -> o1 != o2);
            assertSingletonEquals(singleton1, getter.apply(singletonClass), "ClassLoader - instance");
        } catch (Exception e) {
            logException(e);
        }
    }

}
