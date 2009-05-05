package rmiage.server.modules.demo.singleton;

import rmiage.server.modules.Module;

public class SingletonModule implements Module {

    private static SingletonModule instance = null;

    public static SingletonModule getInstance() {
        if (instance == null) {
            instance = new SingletonModule();
        }
        System.out.println("SingletonModule provided.");
        return instance;

    }

    protected SingletonModule() {
        super();
        System.out.println("SingletonModule created!");
    }

    @Override
    public void finalize() {
        System.out.println("SingletonModule destroyed!");
    }
}
