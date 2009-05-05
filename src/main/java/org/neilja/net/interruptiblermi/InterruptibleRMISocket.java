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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

/**
 * Abstract class to that decorates a Socket to return instances of
 * {@link org.neilja.net.interruptiblermi.InterruptibleRMISocketInputStream} and
 * {@link org.neilja.net.interruptiblermi.InterruptibleRMISocketOutputStream} when
 * {@link #getInputStream()} and {@link #getOutputStream()} are called. Note that
 * {@link java.nio.channels.SocketChannel} is not currently supported - the {@link #getChannel()} method
 * always returns null. Otherwise all other Socket methods are forwarded to the decorated socket.
 * In practice use the concrete subclasses for client and server-side sockets. These subclasses
 * at a minimum must implement the {@link #ioStarting()} and {@link #ioEnding()} methods.
 * 
 * @see org.neilja.net.interruptiblermi.InterruptibleRMIClientSocket
 * @see org.neilja.net.interruptiblermi.InterruptibleRMIServerSideSocket
 * @author neilotoole@apache.org
 */
abstract class InterruptibleRMISocket extends Socket
{

	/*
	 * Special value to indicate that the server side socket should shutdown.
	 */
	protected static final byte SHUTDOWN_SOCKET = Byte.MAX_VALUE;

	protected final Socket decoratee;



	/**
	 * Create a decorator for the given socket.
	 */
	InterruptibleRMISocket(final Socket decoratee)
	{
		this.decoratee = decoratee;
	}



	/**
	 * Called by {@link InterruptibleRMISocketInputStream} and
	 * {@link InterruptibleRMISocketOutputStream} before the thread enters an RMI IO operation.
	 */
	abstract void ioStarting();



	/**
	 * Called by {@link InterruptibleRMISocketInputStream} and
	 * {@link InterruptibleRMISocketOutputStream} after the thread exits an RMI IO operation.
	 */
	abstract void ioEnding();



	/**
	 * @see Socket#bind(java.net.SocketAddress)
	 */
	@Override
	public void bind(final SocketAddress bindpoint) throws IOException
	{
		this.decoratee.bind(bindpoint);
	}



	/**
	 * @see Socket#close()
	 */
	@Override
	public void close() throws IOException
	{
		this.decoratee.close();
	}



	/**
	 * @see Socket#connect(java.net.SocketAddress)
	 */
	@Override
	public void connect(final SocketAddress endpoint) throws IOException
	{
		this.decoratee.connect(endpoint);
	}



	/**
	 * @see Socket#connect(java.net.SocketAddress, int)
	 */
	@Override
	public void connect(final SocketAddress endpoint, final int timeout) throws IOException
	{
		this.decoratee.connect(endpoint, timeout);
	}



	/**
	 * Return true if the object is an instance of InterruptibleRMIClientSocket and
	 * decoratee.equals(object) returns true.
	 */
	@Override
	public boolean equals(final Object object)
	{
		return object instanceof InterruptibleRMISocket
			&& this.decoratee.equals(((InterruptibleRMISocket) object).decoratee);
	}



	/**
	 * This decorator does not implement a decorated SocketChannel. This method will always return
	 * null.
	 * 
	 */
	@Override
	public SocketChannel getChannel()
	{
		return null;
	}



	/**
	 * @see Socket#getInetAddress()
	 */
	@Override
	public InetAddress getInetAddress()
	{
		return this.decoratee.getInetAddress();
	}



	/**
	 * Return an instance of {@link InterruptibleRMISocketInputStream} that decorates the
	 * InputStream returned by decoratee#getInputStream.
	 * 
	 * @see Socket#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException
	{
		return new InterruptibleRMISocketInputStream(this, this.decoratee.getInputStream());
	}



	/**
	 * @see Socket#getKeepAlive()
	 */
	@Override
	public boolean getKeepAlive() throws SocketException
	{
		return this.decoratee.getKeepAlive();
	}



	/**
	 * @see Socket#getLocalAddress()
	 */
	@Override
	public InetAddress getLocalAddress()
	{
		return this.decoratee.getLocalAddress();
	}



	/**
	 * @see Socket#getLocalPort()
	 */
	@Override
	public int getLocalPort()
	{
		return this.decoratee.getLocalPort();
	}



	/**
	 * @see Socket#getLocalSocketAddress()
	 */
	@Override
	public SocketAddress getLocalSocketAddress()
	{
		return this.decoratee.getLocalSocketAddress();
	}



	/**
	 * @see Socket#getOOBInline()
	 */
	@Override
	public boolean getOOBInline() throws SocketException
	{
		return this.decoratee.getOOBInline();
	}



	/**
	 * Return an instance of {@link InterruptibleRMISocketOutputStream} that decorates the
	 * OutputStream returned by decoratee#getOutputStream
	 * 
	 * @see Socket#getInputStream()
	 */
	@Override
	public OutputStream getOutputStream() throws IOException
	{
		return new InterruptibleRMISocketOutputStream(this, this.decoratee.getOutputStream());
	}



	/**
	 * @see Socket#getPort()
	 */
	@Override
	public int getPort()
	{
		return this.decoratee.getPort();
	}



	/**
	 * @see Socket#getReceiveBufferSize()
	 */
	@Override
	public int getReceiveBufferSize() throws SocketException
	{
		return this.decoratee.getReceiveBufferSize();
	}



	/**
	 * @see Socket#getRemoteSocketAddress()
	 */
	@Override
	public SocketAddress getRemoteSocketAddress()
	{
		return this.decoratee.getRemoteSocketAddress();
	}



	/**
	 * @see Socket#getReuseAddress()
	 */
	@Override
	public boolean getReuseAddress() throws SocketException
	{
		return this.decoratee.getReuseAddress();
	}



	/**
	 * @see Socket#getSendBufferSize()
	 */
	@Override
	public int getSendBufferSize() throws SocketException
	{
		return this.decoratee.getSendBufferSize();
	}



	/**
	 * @see Socket#getSoLinger()
	 */
	@Override
	public int getSoLinger() throws SocketException
	{
		return this.decoratee.getSoLinger();
	}



	/**
	 * @see Socket#getSoTimeout()
	 */
	@Override
	public int getSoTimeout() throws SocketException
	{
		return this.decoratee.getSoTimeout();
	}



	/**
	 * @see Socket#getTcpNoDelay()
	 */
	@Override
	public boolean getTcpNoDelay() throws SocketException
	{
		return this.decoratee.getTcpNoDelay();
	}



	/**
	 * @see Socket#getTrafficClass()
	 */
	@Override
	public int getTrafficClass() throws SocketException
	{
		return this.decoratee.getTrafficClass();
	}



	/**
	 * Return decoratee#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return this.decoratee.hashCode();
	}



	/**
	 * @see Socket#isBound()
	 */
	@Override
	public boolean isBound()
	{
		return this.decoratee.isBound();
	}



	/**
	 * @see Socket#isClosed()
	 */
	@Override
	public boolean isClosed()
	{
		return this.decoratee.isClosed();
	}



	/**
	 * @see Socket#isConnected()
	 */
	@Override
	public boolean isConnected()
	{
		return this.decoratee.isConnected();
	}



	/**
	 * @see Socket#isInputShutdown()
	 */
	@Override
	public boolean isInputShutdown()
	{
		return this.decoratee.isInputShutdown();
	}



	/**
	 * @see Socket#isOutputShutdown()
	 */
	@Override
	public boolean isOutputShutdown()
	{
		return this.decoratee.isOutputShutdown();
	}



	/**
	 * @see Socket#sendUrgentData(int)
	 */
	@Override
	public void sendUrgentData(final int data) throws IOException
	{
		this.decoratee.sendUrgentData(data);
	}



	/**
	 * @see Socket#setKeepAlive(boolean)
	 */
	@Override
	public void setKeepAlive(final boolean on) throws SocketException
	{
		this.decoratee.setKeepAlive(on);
	}



	/**
	 * @see Socket#setOOBInline(boolean)
	 */
	@Override
	public void setOOBInline(final boolean on) throws SocketException
	{
		this.decoratee.setOOBInline(on);
	}



	/**
	 * @see Socket#setPerformancePreferences(int, int, int)
	 */
	@Override
	public void setPerformancePreferences(final int connectionTime, final int latency,
		final int bandwidth)
	{
		this.decoratee.setPerformancePreferences(connectionTime, latency, bandwidth);
	}



	/**
	 * @see Socket#setReceiveBufferSize(int)
	 */
	@Override
	public void setReceiveBufferSize(final int size) throws SocketException
	{
		this.decoratee.setReceiveBufferSize(size);
	}



	/**
	 * @see Socket#setReuseAddress(boolean)
	 */
	@Override
	public void setReuseAddress(final boolean on) throws SocketException
	{
		this.decoratee.setReuseAddress(on);
	}



	/**
	 * @see Socket#setSendBufferSize(int)
	 */
	@Override
	public void setSendBufferSize(final int size) throws SocketException
	{
		this.decoratee.setSendBufferSize(size);
	}



	/**
	 * @see Socket#setSoLinger(boolean, int)
	 */
	@Override
	public void setSoLinger(final boolean on, final int linger) throws SocketException
	{
		this.decoratee.setSoLinger(on, linger);
	}



	/**
	 * @see Socket#setSoTimeout(int)
	 */
	@Override
	public void setSoTimeout(final int timeout) throws SocketException
	{
		this.decoratee.setSoTimeout(timeout);
	}



	/**
	 * @see Socket#setTcpNoDelay(boolean)
	 */
	@Override
	public void setTcpNoDelay(final boolean on) throws SocketException
	{
		this.decoratee.setTcpNoDelay(on);
	}



	/**
	 * @see Socket#setTrafficClass(int)
	 */
	@Override
	public void setTrafficClass(final int tc) throws SocketException
	{
		this.decoratee.setTrafficClass(tc);
	}



	/**
	 * @see Socket#shutdownInput()
	 */
	@Override
	public void shutdownInput() throws IOException
	{
		this.decoratee.shutdownInput();
	}



	/**
	 * @see Socket#shutdownOutput()
	 */
	@Override
	public void shutdownOutput() throws IOException
	{
		this.decoratee.shutdownOutput();
	}



	/**
	 * Return decoratee#toString()
	 */
	@Override
	public String toString()
	{
		return this.decoratee.toString();
	}

}
