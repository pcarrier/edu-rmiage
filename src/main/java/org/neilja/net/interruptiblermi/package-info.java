/**
 * <p>
 * This package provides a mechanism for interrupting RMI calls. An RMISocketFactory implementation
 * is provided ({@link org.neilja.net.interruptiblermi.InterruptibleRMISocketFactory}), as well as
 * a ThreadFactory ({@link org.neilja.net.interruptiblermi.InterruptibleRMIThreadFactory}).
 * 
 * Install the RMISocketFactory in the standard way. This can be done by the RMI server binding the
 * factory when calling
 * {@link java.rmi.server.UnicastRemoteObject#exportObject(Remote, int, RMIClientSocketFactory, RMIServerSocketFactory)}
 * or by the client calling
 * {@link java.rmi.server.RMISocketFactory#setSocketFactory(RMISocketFactory)}.
 * </p>
 * 
 * <h3>Client Side</h3>
 * <p>
 * From the client, use a thread from <code>InterruptibleRMIThreadFactory</code> to make RMI
 * calls. Invoking <code>#interrupt()</code> on that thread will result in the RMI IO operation
 * being terminated and the IO method returning.
 * </p>
 * 
 * <p>
 * Typically a blocking IO call cannot be interrupted, but this limitation can be circumvented by
 * directly closing the IO object (i.e. the socket). An RMI client socket object returned by
 * <code>InterruptibleRMISocketFactory</code> registers with the thread instance returned by
 * <code>InterruptibleRMIThreadFactory</code> when the socket is about to enter a blocking IO
 * operation (and unregisters on exit of that IO operation). So, when <code>#interrupt()</code> is
 * invoked, the thread has a reference to the socket object that is currently in blocking IO. The
 * thread directly calls <code>#close()</code> on that socket (after sending a shutdown signal to
 * the RMI server), terminating the blocking IO operation, and effectively simulating a regular
 * interrupt. Note that after the interrupt, the socket is now dead, and the thread's interrupt
 * status has been set.
 * </p>
 * 
 * <h3>Server Side</h3>
 * <p>
 * When the client interrupts the RMI call, the server RMI thread that was spawned in response to
 * that RMI call is typically still alive. If the server RMI thread had been waiting for a resource,
 * e.g. waiting for a lock on synchronized object such as a database row, then the thread would not
 * know that the client had interupted the RMI call, and thus the "zombie" thread could acquire the
 * contested resource, thus possibly denying a healthy thread access to the resource. To combat this
 * situation, a mapping is maintained between server RMI threads and the RMI socket that spawned
 * that thread. If the <code>#close</code> method is invoked on the server RMI socket,
 * <code>Thread#interrupt</code> is called on on the zombie thread. At this point, or at any point
 * that the server RMI thread can potentially acquire a contested resource, the "zombie" status of
 * the thread can be tested using
 * {@link org.neilja.net.interruptiblermi.InterruptibleRMISocketFactory#isCurrentRMIServerThreadSocketAlive()}.
 * If this method returns true, the zombie RMI server thread should attempt to die (either by
 * returning immediately or throwing an exception as appropriate to your application).
 * </p>
 * 
 * <p>
 * The code snippet below shows how the server RMI thread can be a good citizen:
 * 
 * <pre>
 * while (isContestedResourceAvailable == false)
 * {
 * 	this.wait();
 * 
 * 	// on wakeup
 * 	if (InterruptibleRMISocketFactory.isCurrentRMIServerThreadSocketAlive() == false)
 * 	{
 * 		// this thread is a &quot;zombie&quot; thread - time to die!
 * 		throw new RuntimeException(&quot;The RMI socket associated with this thread is not alive&quot;);
 * 	}
 * 
 * 	// otherwise, do something fun with the acquired resource...
 * }
 * </pre>
 * 
 * </p>
 * <p>
 * However, note that closing of the RMI socket by the client does not necessarily result in the
 * server RMI thread knowing that the client has closed its end of the socket (at least this is the
 * case with the Sun JDK 5.0 implementation). That is, calling
 * <code>myServerRMISocket.isClosed()</code> doesn't necessarily return true even after the client
 * has interrupted the RMI call and invoked <code>myClientRMISocket.close()</code>. To signal
 * that the client has shutdown the socket, the <code>#interrupt()</code> method on thread
 * instances returned by <code>InterruptibleRMIThreadFactory</code> writes a shutdown signal (a
 * byte value) to the client RMI socket's output stream before closing the socket. So when the
 * zombie RMI server thread invokes
 * <code>InterruptibleRMISocketFactory#isCurrentRMIServerThreadSocketAlive()</code>, that method
 * checks if this byte value has been written to the thread's RMI socket, and can thus determine
 * that the RMI call has indeed been interrupted.
 * </p>
 * 
 * 
 * @author neilotoole@apache.org
 * @see org.neilja.net.interruptiblermi.InterruptibleRMISocketFactory
 * @see org.neilja.net.interruptiblermi.InterruptibleRMIThreadFactory
 * @see java.lang.Thread#interrupt()
 */

package org.neilja.net.interruptiblermi;

import java.rmi.Remote;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RMISocketFactory;

