package rmiage.server.search;

import java.util.Collection;
import rmiage.server.storage.Content;

public interface SearchEngine {
    public <T extends Content, C extends Collection<T>> C search(Request R);
}