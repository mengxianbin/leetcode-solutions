package singleton;

import java.io.Serializable;

public class SingletonNormal implements Cloneable, Serializable {

    private static final SingletonNormal instance = new SingletonNormal();

    private SingletonNormal() {

    }

    public static SingletonNormal getInstance() {
        return instance;
    }

    /**
     * Cannot be omitted !!!
     */
    @Override
    public Object clone() {
        return instance;
    }

    /**
     * Cannot be omitted !!!
     */
    private Object readResolve() {
        return instance;
    }

}
