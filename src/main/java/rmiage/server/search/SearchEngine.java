package rmiage.server.search;

import java.util.Collection;

/**
 * An interface which gives the possibility to make
 * a quick search in whatever it is implemented.
 */
public interface SearchEngine {

    public <T, C extends Collection<T>> C search(Request R);
}