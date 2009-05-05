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

/**
 * Decorator object for InputStream that invokes the listener's #ioStarting and #ioEnding methods
 * when entering and exiting IO blocks. The IO methods of this class are generally synchronized,
 * with the notable exception of #close.
 * 
 * 
 * @author neilotoole@apache.org
 */
class InterruptibleRMISocketInputStream extends InputStream
{
	private final InputStream decoratee;

	private final InterruptibleRMISocket listener;



	/**
	 * Create a new InterruptibleRMISocketInputStream instance that decorates the supplied
	 * InputStream and calls back to the supplied listener.
	 * 
	 * 
	 * @param listener
	 *            the listener to call back to
	 * @param decoratee
	 *            the InputStream to decorate
	 */
	InterruptibleRMISocketInputStream(final InterruptibleRMISocket listener,
		final InputStream decoratee)
	{
		this.decoratee = decoratee;
		this.listener = listener;
	}



	/**
	 * @see InputStream#available()
	 */
	@Override
	public synchronized int available() throws IOException
	{
		return this.decoratee.available();
	}



	/**
	 * Close the underlying OutputStream. Unlike the #read methods, this method is not synchronized,
	 * and it does not call back to the listener.
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
	 * Return true if the supplied object is also an instance of InterruptibleRMISocketInputStream,
	 * and if the delegate and listener members are equal for both objects. Generally speaking IO
	 * objects do not override #equals, so this method will tend to return false unless the
	 * decorated InputStream class has itself overridden #equals.
	 */
	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof InterruptibleRMISocketInputStream
			&& this.decoratee.equals(((InterruptibleRMISocketInputStream) obj).decoratee)
			&& this.listener.equals(((InterruptibleRMISocketInputStream) obj).listener);
	}



	/**
	 * Return delegate#hashCode() ^ listener#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return this.decoratee.hashCode() ^ this.listener.hashCode();
	}



	/**
	 * @see InputStream#mark(int)
	 */
	@Override
	public synchronized void mark(final int readlimit)
	{
		this.decoratee.mark(readlimit);
	}



	/**
	 * @see InputStream#markSupported()
	 */
	@Override
	public boolean markSupported()
	{
		return this.decoratee.markSupported();
	}



	/**
	 * @see InputStream#read()
	 */
	@Override
	public synchronized int read() throws IOException
	{
		try
		{
			this.listener.ioStarting();
			return this.decoratee.read();
		}
		finally
		{
			this.listener.ioEnding();
		}

	}



	/**
	 * @see InputStream#read(byte[])
	 */
	@Override
	public synchronized int read(final byte[] b) throws IOException
	{
		try
		{
			this.listener.ioStarting();
			return this.decoratee.read(b);
		}
		finally
		{
			this.listener.ioEnding();
		}
	}



	/**
	 * @see InputStream#read(byte[], int, int)
	 */
	@Override
	public synchronized int read(final byte[] b, final int off, final int len) throws IOException
	{
		try
		{
			this.listener.ioStarting();
			return this.decoratee.read(b, off, len);
		}
		finally
		{
			this.listener.ioEnding();
		}
	}



	/**
	 * @see InputStream#reset()
	 */
	@Override
	public synchronized void reset() throws IOException
	{
		this.decoratee.reset();
	}



	/**
	 * @see InputStream#skip(long)
	 */
	@Override
	public synchronized long skip(final long n) throws IOException
	{
		try
		{
			this.listener.ioStarting();
			return this.decoratee.skip(n);
		}
		finally
		{
			this.listener.ioEnding();
		}
	}



	/**
	 * Return this.getClass().getName() + [decoratee.toString()]
	 */
	@Override
	public String toString()
	{
		return this.getClass().getName() + " [" + this.decoratee.toString() + "]";
	}
}
