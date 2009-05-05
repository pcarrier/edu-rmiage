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
    
	public static List<Class> getClasses(String[] classNames){
    	ArrayList<Class> ret = new ArrayList<Class>();
    	for(String name: classNames){
    		ret.add( createClass(name));
    	}
    	return (List<Class>)ret;
    }
	/**
	 * 
	 * @param classNames
	 * @return
	 */
	public static List<Object> getInstances(String[] classNames){
    	ArrayList<Object> ret = new ArrayList<Object>();
    	for(String name: classNames){
    		ret.add( createInstance(name));
    	}
    	return (List<Object>)ret;
    }
}