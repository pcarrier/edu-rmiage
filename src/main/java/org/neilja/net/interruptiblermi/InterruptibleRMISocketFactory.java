/**
 * Copyright 2005 Neil O'Toole - neilotoole@apache.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.neilja.net.interruptiblermi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RMISocketFactory;

/**
 * Decorates RMISocketFactory to return Socket and ServerSocket instances that support interruptible
 * RMI.
 * 
 * @author neilotoole@apache.org
 */
public class InterruptibleRMISocketFactory extends RMISocketFactory
	implements
		RMIClientSocketFactory,
		RMIServerSocketFactory,
		Serializable
{
	/**
	 * For serialization support.
	 */
	private static final long serialVersionUID = 42L;

	/**
	 * The factory that provides the underlying socket instances.
	 */
	private RMISocketFactory factory;



	/**
	 * Create a new instance of this class, returning decorated sockets from
	 * {@link RMISocketFactory#getDefaultSocketFactory()}.
	 */
	public InterruptibleRMISocketFactory()
	{
		this.factory = RMISocketFactory.getDefaultSocketFactory();
	}



	/**
	 * Create a new instance of this class, returning decorated sockets from the supplied factory.
	 */
	public InterruptibleRMISocketFactory(final RMISocketFactory factory)
	{
		this.factory = factory;
	}



	/**
	 * Override #writeObject, as there is no need to write anything to the ObjectOutputStream. This
	 * class has holds no instance-specific data.
	 * 
	 */
	@SuppressWarnings("unused")
	private void writeObject(final ObjectOutputStream out)
	{
	/*
	 * don't need to do anything here
	 */
	}



	/**
	 * Override #readObject to initialize the this.factory member to the default RMISocketFactory.
	 */
	@SuppressWarnings("unused")
	private void readObject(final ObjectInputStream in)
	{
		this.factory = RMISocketFactory.getDefaultSocketFactory();
	}



	/**
	 * Create a new Socket supporting interruptible RMI.
	 * 
	 * @see RMISocketFactory#createSocket(java.lang.String, int)
	 */
	@Override
	public Socket createSocket(final String host, final int port) throws IOException
	{
		return new InterruptibleRMIClientSocket(this.factory.createSocket(host, port));
	}



	/**
	 * Return true if object is an instance of InterruptibleRMISocketFactory.
	 */
	@Override
	public boolean equals(final Object object)
	{
		return object instanceof InterruptibleRMISocketFactory;
	}



	/**
	 * Return the hashCode value of this class's name.
	 */
	@Override
	public int hashCode()
	{
		return this.getClass().getName().hashCode();
	}



	/**
	 * Return the name of this class.
	 */
	@Override
	public String toString()
	{
		return this.getClass().getName();
	}



	/**
	 * Return a new ServerSocket supporting interruptible RMI.
	 * 
	 * @see RMISocketFactory#createServerSocket(int)
	 */
	@Override
	public synchronized ServerSocket createServerSocket(final int port) throws IOException
	{
		return new InterruptibleRMIServerSocket(this.factory.createServerSocket(port));
	}



	/**
	 * Return true if the socket associated with the RMI server thread (the current thread) has not
	 * been closed or marked for shutdown by the client of the RMI thread. This method should only
	 * be called by a server RMI thread (spawned in response to an RMI call) to check the state of
	 * its own RMI socket.
	 */
	public static boolean isCurrentRMIServerThreadSocketAlive()
	{
		/*
		 * This method delegates to the namesake method in InterruptibleRMIServerSideSocket. The
		 * only classes in this package that are public are the factory classes: this class, and
		 * InterruptibleRMIThreadFactory, and allowing public access to the delegated method would
		 * require making InterruptibleRMIServerSideSocket public as well. Being that the delegated
		 * method is the only aspect of that class that needs to be publicly accessed, it makes for
		 * a cleaner design to keep that class non-public, and provide access through this public
		 * method.
		 */
		return InterruptibleRMIServerSideSocket.isCurrentRMIServerThreadSocketAlive();
	}

}
