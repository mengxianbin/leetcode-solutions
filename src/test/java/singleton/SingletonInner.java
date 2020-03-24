package singleton;

import java.io.Serializable;

public class SingletonInner implements Cloneable, Serializable {

    private SingletonInner() {

    }

    public static SingletonInner getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static final SingletonInner instance = new SingletonInner();
    }

    /**
     * Cannot be omitted !!!
     */
    @Override
    public Object clone() {
        return InstanceHolder.instance;
    }

    /**
     * Cannot be omitted !!!
     */
    private Object readResolve() {
        return InstanceHolder.instance;
    }

}
