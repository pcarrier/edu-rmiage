package rmiage.server.storage;

import rmiage.framework.data.IContent;

public interface IBackend {
	public String add(IContent o);

	public void remove(String identifier);
	
	public IContent get(String identifier);
	
	public void update(String identifier, IContent object) throws Exception;
}
