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
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Socket decorator to support the interruptible RMI mechanism.
 * 
 * @see org.neilja.net.interruptiblermi.InterruptibleRMISocketFactory
 * @author neilotoole@apache.org
 * 
 * 
 */
class InterruptibleRMIServerSideSocket extends InterruptibleRMISocket
{
	/**
	 * A mapping of server RMI threads to the sockets that the thread is using for RMI calls.
	 */
	private static Map<Thread, InterruptibleRMIServerSideSocket> threadSocketMap;

	/**
	 * A mapping of RMI sockets to the server RMI threads using the sockets.
	 */
	private static Map<InterruptibleRMIServerSideSocket, Thread> socketThreadMap;



	/**
	 * (Lazily) initialize the maps (and exception handler) for managing server RMI socket/thread
	 * tracking.
	 * 
	 */
	private static void initializeServerThreadSocketMapping()
	{
		threadSocketMap = new HashMap<Thread, InterruptibleRMIServerSideSocket>();
		socketThreadMap = new HashMap<InterruptibleRMIServerSideSocket, Thread>();
	}



	/**
	 * Called by {@link #ioStarting()} to associate a server RMI thread (the current thread) with
	 * the supplied socket.
	 */
	private synchronized static void registerThreadIsUsingSocket(
		final InterruptibleRMIServerSideSocket socket)
	{
		if (threadSocketMap == null)
		{
			initializeServerThreadSocketMapping();
		}

		/*
		 * Clear out any previously registered thread
		 */
		final Thread previousThread = socketThreadMap.remove(socket);

		if (previousThread != null)
		{
			threadSocketMap.remove(previousThread);
		}

		/*
		 * Associate the thread and the socket.
		 */
		threadSocketMap.put(Thread.currentThread(), socket);
		socketThreadMap.put(socket, Thread.currentThread());
	}



	/**
	 * Called when the socket's {@link #close()} method is invoked. This method disassociates the
	 * socket from its associated RMI server thread, and calls {@link Thread#interrupt()} on the
	 * thread in case it is currently waiting.
	 * 
	 */
	private synchronized static void registerSocketIsClosing(
		final InterruptibleRMIServerSideSocket socket)
	{
		if (threadSocketMap == null)
		{
			return;
		}

		final Thread thread = socketThreadMap.remove(socket);

		if (thread != null)
		{
			threadSocketMap.remove(socket);
			thread.interrupt();
		}
	}



	/**
	 * Return true if the socket associated with the RMI server thread (the current thread) has not
	 * been closed or marked for shutdown by the client of the RMI thread.
	 */
	static synchronized boolean isCurrentRMIServerThreadSocketAlive()
	{
		if (threadSocketMap == null)
		{
			return true;
		}

		final Socket socket = threadSocketMap.get(Thread.currentThread());

		boolean isAlive = true;

		try
		{
			if (socket == null || socket.isClosed())
			{
				isAlive = false;
			}
			else
			{
				/*
				 * When a client explicitly interrupts the InterruptibleRMIThread, the special value
				 * SHUTDOWN_SOCKET is written to the socket indicating it should shut down.
				 */

				final InputStream in = socket.getInputStream();

				if (in.available() > 0)
				{
					final int val = in.read();

					if (val == InterruptibleRMISocket.SHUTDOWN_SOCKET)
					{
						isAlive = false;
					}
				}
			}

		}
		catch (final Exception e)
		{
			/*
			 * If an exception occurs while calling an operation on the socket, then it's fair to
			 * assume that the socket is dead.
			 */
			isAlive = false;
		}

		return isAlive;
	}

	private Thread mostRecentThread;



	/**
	 * 
	 * @see InterruptibleRMISocket#InterruptibleRMISocket(Socket)
	 */
	InterruptibleRMIServerSideSocket(final Socket decoratee)
	{
		super(decoratee);

	}



	/**
	 * Does nothing.
	 * 
	 * @see InterruptibleRMISocket#ioEnding()
	 */
	@Override
	void ioEnding()
	{
	// do nothing
	}



	/**
	 * If not already associated, this method associates the current thread with this socket.
	 * 
	 * @see InterruptibleRMISocket#ioStarting()
	 */
	@Override
	void ioStarting()
	{
		if (Thread.currentThread() != this.mostRecentThread)
		{
			this.mostRecentThread = Thread.currentThread();
			registerThreadIsUsingSocket(this);
		}
	}



	/**
	 * Closes the decorated socket and then disassociates the current thread from this socket.
	 * 
	 * @see InterruptibleRMISocket#close()
	 */
	@Override
	public void close() throws IOException
	{
		this.decoratee.close();
		registerSocketIsClosing(this);
	}

}
