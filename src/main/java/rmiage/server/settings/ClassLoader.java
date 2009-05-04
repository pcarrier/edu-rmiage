package rmiage.server.settings;

public class ClassLoader {

    public static Class loadClass(String name) {
        Class res = null;
        try {
            res = Class.forName(name);
        } catch (ClassNotFoundException ex) {
            // TODO
            System.err.println("TODO " + ex);
        } finally {
            return res;
        }
    }

    public static Object createInstance(String name) {
        Object res = null;
        try {
            res = loadClass(name).newInstance();
        } catch (InstantiationException ex) {
            //TODO
        } catch (IllegalAccessException ex) {
            //TODO
        } finally {
            return res;
        }
    }
}