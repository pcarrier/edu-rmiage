package rmiage.server.search;

import java.util.Collection;
import rmiage.server.storage.Content;

/**
 * An interface which gives the possibility to make
 * a quick search in whatever it is implemented.
 */

public interface SearchEngine {
    public <T extends Content, C extends Collection<T>> C search(Request R);
}