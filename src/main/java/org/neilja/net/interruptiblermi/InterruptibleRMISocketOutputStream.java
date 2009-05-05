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
import java.io.OutputStream;

/**
 * Decorator object for OutputStream that invokes the listener's #ioStarting and #ioEnding methods
 * when entering and exiting IO blocks. The #flush and #write methods in this class are
 * synchronized.
 * 
 * 
 * @author neilotoole@apache.org
 */
class InterruptibleRMISocketOutputStream extends OutputStream
{
	private final OutputStream decoratee;

	private final InterruptibleRMISocket listener;



	/**
	 * Create a new InterruptibleRMISocketOutputStream instance that decorates the supplied
	 * OutputStream and calls back to the supplied listener.
	 * 
	 * 
	 * @param listener
	 *            the listener to callback to
	 * @param decoratee
	 *            the OutputStream to decorate
	 */
	InterruptibleRMISocketOutputStream(final InterruptibleRMISocket listener,
		final OutputStream decoratee)
	{
		this.decoratee = decoratee;
		this.listener = listener;
	}



	/**
	 * @see OutputStream#write(int)
	 */
	@Override
	public synchronized void write(final int b) throws IOException
	{
		try
		{
			this.listener.ioStarting();
			this.decoratee.write(b);
		}
		finally
		{
			this.listener.ioEnding();
		}
	}



	/**
	 * Close the underlying OutputStream. Unlike the #flush and #write methods, this method is not
	 * synchronized, and it does not call back to the listener.
	 * 
	 * 
	 * @see OutputStream#close()
	 */
	@Override
	public void close() throws IOException
	{
		this.decoratee.close();
	}



	/**
	 * Return true if the supplied object is also an instance of InterruptibleRMISocketOutputStream,
	 * and if the delegate and listener members are equal for both objects. Generally speaking IO
	 * objects do not override #equals, so this method will tend to return false unless the
	 * decorated OutputStream class has overridden #equals.
	 */
	@Override
	public boolean equals(final Object obj)
	{

		return obj instanceof InterruptibleRMISocketOutputStream
			&& this.decoratee.equals(((InterruptibleRMISocketOutputStream) obj).decoratee)
			&& this.listener.equals(((InterruptibleRMISocketOutputStream) obj).listener);
	}



	/**
	 * @see OutputStream#flush()
	 */
	@Override
	public synchronized void flush() throws IOException
	{
		try
		{
			this.listener.ioStarting();
			this.decoratee.flush();
		}
		finally
		{
			this.listener.ioEnding();
		}
	}



	/**
	 * Return decoratee#hashCode() ^ listener#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return this.decoratee.hashCode() ^ this.listener.hashCode();
	}



	/**
	 * Return this.getClass().getName() + [decoratee.toString()]
	 */
	@Override
	public String toString()
	{
		return this.getClass().getName() + " [" + this.decoratee.toString() + "]";
	}



	/**
	 * @see OutputStream#write(byte[])
	 */
	@Override
	public synchronized void write(final byte[] b) throws IOException
	{
		try
		{
			this.listener.ioStarting();
			this.decoratee.write(b);
		}
		finally
		{
			this.listener.ioEnding();
		}
	}



	/**
	 * @see OutputStream#write(byte[], int, int)
	 */
	@Override
	public synchronized void write(final byte[] b, final int off, final int len) throws IOException
	{
		try
		{
			this.listener.ioStarting();
			this.decoratee.write(b, off, len);
		}
		finally
		{
			this.listener.ioEnding();
		}
	}
}
