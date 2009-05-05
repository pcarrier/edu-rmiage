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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.ServerSocketChannel;

/**
 * Socket decorator for ServerSocket to support the interrupible RMI mechanism.
 * 
 * @see org.neilja.net.interruptiblermi.InterruptibleRMISocketFactory
 * @author neilotoole@apache.org
 * 
 */
class InterruptibleRMIServerSocket extends ServerSocket
{
	private final ServerSocket decoratee;



	/**
	 * Create decorator for given socket.
	 */
	InterruptibleRMIServerSocket(final ServerSocket decoratee) throws IOException
	{
		this.decoratee = decoratee;
	}



	/**
	 * @see ServerSocket#accept()
	 */
	@Override
	public Socket accept() throws IOException
	{
		return new InterruptibleRMIServerSideSocket(this.decoratee.accept());
	}



	/**
	 * @see ServerSocket#bind(java.net.SocketAddress, int)
	 */
	@Override
	public void bind(final SocketAddress endpoint, final int backlog) throws IOException
	{
		this.decoratee.bind(endpoint, backlog);
	}



	/**
	 * @see ServerSocket#bind(java.net.SocketAddress)
	 */
	@Override
	public void bind(final SocketAddress endpoint) throws IOException
	{
		this.decoratee.bind(endpoint);
	}



	/**
	 * @see ServerSocket#close()
	 */
	@Override
	public void close() throws IOException
	{
		this.decoratee.close();
	}



	/**
	 * Returns true if the decoratee's equals method returns true.
	 */
	@Override
	public boolean equals(final Object obj)
	{
		return this.decoratee.equals(obj);
	}



	/**
	 * Not implemented - always returns null.
	 * 
	 * @see ServerSocket#getChannel()
	 */
	@Override
	public ServerSocketChannel getChannel()
	{
		return null;
	}



	/**
	 * @see ServerSocket#getInetAddress()
	 */
	@Override
	public InetAddress getInetAddress()
	{
		return this.decoratee.getInetAddress();
	}



	/**
	 * @see ServerSocket#getLocalPort()
	 */
	@Override
	public int getLocalPort()
	{
		return this.decoratee.getLocalPort();
	}



	/**
	 * @see ServerSocket#getLocalSocketAddress()
	 */
	@Override
	public SocketAddress getLocalSocketAddress()
	{
		return this.decoratee.getLocalSocketAddress();
	}



	/**
	 * @see ServerSocket#getReceiveBufferSize()
	 */
	@Override
	public int getReceiveBufferSize() throws SocketException
	{
		return this.decoratee.getReceiveBufferSize();
	}



	/**
	 * @see ServerSocket#getReuseAddress()
	 */
	@Override
	public boolean getReuseAddress() throws SocketException
	{
		return this.decoratee.getReuseAddress();
	}



	/**
	 * @see ServerSocket#getSoTimeout()
	 */
	@Override
	public int getSoTimeout() throws IOException
	{
		return this.decoratee.getSoTimeout();
	}



	/**
	 * Return the hashcode of the decorated socket.
	 */
	@Override
	public int hashCode()
	{
		return this.decoratee.hashCode();
	}



	/**
	 * @see ServerSocket#isBound()
	 */
	@Override
	public boolean isBound()
	{
		return this.decoratee.isBound();
	}



	/**
	 * @see ServerSocket#isClosed()
	 */
	@Override
	public boolean isClosed()
	{
		return this.decoratee.isClosed();
	}



	/**
	 * @see ServerSocket#setPerformancePreferences(int, int, int)
	 */
	@Override
	public void setPerformancePreferences(final int connectionTime, final int latency,
		final int bandwidth)
	{
		this.decoratee.setPerformancePreferences(connectionTime, latency, bandwidth);
	}



	/**
	 * @see ServerSocket#setReceiveBufferSize(int)
	 */
	@Override
	public void setReceiveBufferSize(final int size) throws SocketException
	{
		this.decoratee.setReceiveBufferSize(size);
	}



	/**
	 * @see ServerSocket#setReuseAddress(boolean)
	 */
	@Override
	public void setReuseAddress(final boolean on) throws SocketException
	{
		this.decoratee.setReuseAddress(on);
	}



	/**
	 * @see ServerSocket#setSoTimeout(int)
	 */
	@Override
	public void setSoTimeout(final int timeout) throws SocketException
	{
		this.decoratee.setSoTimeout(timeout);
	}



	/**
	 * @see ServerSocket#toString()
	 */
	@Override
	public String toString()
	{
		return this.decoratee.toString();
	}

}
