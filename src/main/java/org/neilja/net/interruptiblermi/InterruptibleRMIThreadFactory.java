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

import java.util.concurrent.ThreadFactory;

/**
 * ThreadFactory implementation to support interruptibility of RMI calls. Use {@link #getInstance()}
 * to get an instance of this class. If an RMI call is made from a thread instance returned by this
 * factory, and the RMI call uses a socket from
 * {@link org.neilja.net.interruptiblermi.InterruptibleRMISocketFactory}, then calling
 * #interrupt on the thread instance while thread is in a blocking RMI IO operation will result in
 * the IO operation terminating. (Typically RMI IO operations cannot be interrupted).
 * 
 * @author neilotoole@apache.org
 * 
 */
public class InterruptibleRMIThreadFactory implements ThreadFactory
{
	/**
	 * Lazily-instatiated singleton instance.
	 */
	private static InterruptibleRMIThreadFactory instance;



	/**
	 * Return a ThreadFactory that creates instances of InterruptibleRMIThread. Note that the
	 * returned ThreadFactory is a singleton.
	 */
	public static ThreadFactory getInstance()
	{
		if (instance == null)
		{
			instance = new InterruptibleRMIThreadFactory();
		}

		return instance;
	}



	private InterruptibleRMIThreadFactory()
	{
	// empty constructor
	}



	/**
	 * Return a new instance of thread instance using the supplied runnable.
	 * 
	 * @see ThreadFactory#newThread(java.lang.Runnable)
	 */
	public Thread newThread(final Runnable r)
	{
		return new InterruptibleRMIThread(r);
	}

}
