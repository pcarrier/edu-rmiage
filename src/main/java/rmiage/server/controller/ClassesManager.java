package rmiage.server.controller;

import java.util.ArrayList;
import java.util.List;

import rmiage.server.modules.ModuleLoader;

public class ClassesManager {

    private ClassesManager() {
    }

    public static Class createClass(String name) {
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

    /**
     * Creates a new Instance  
     * @param InstanceName
     * @return TODO ? :)
     */
    public static Object createInstance(String name) {
        Object res = null;
        try {
            res = createClass(name).newInstance();
        } catch (InstantiationException ex) {
            //TODO
        } catch (IllegalAccessException ex) {
            //TODO
        } finally {
            return res;
        }
    }
    /**
     * Get a List of Classes with the classNames[] names
     * @param classNames
     * @return
     */
    public static <T> List<Class<T>> getClasses(String[] classNames) {
        ArrayList<Class<T>> ret = new ArrayList<Class<T>>();
        for (String name : classNames) {
            ret.add((Class<T>) createClass(name));
        }
        return (List<Class<T>>) ret;
    }

    /**
     * Get a List of Instances with the classNames[] names
     * @param List of classNames
     * @return
     */
    public static <T> List<T> getInstances(String[] classNames) {
    	ArrayList<T> ret = new ArrayList<T>();
        for (String name : classNames) {
            ret.add((T) createInstance(name));
        }
        return (List<T>) ret;
    }
}