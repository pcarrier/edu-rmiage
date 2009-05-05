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

import java.net.Socket;

/**
 * Decorator for an RMI client socket to support interruptible RMI.
 * 
 * @author neilotoole@apache.org
 */
class InterruptibleRMIClientSocket extends InterruptibleRMISocket
{

	/**
	 * Create a decorator for the given socket.
	 */
	InterruptibleRMIClientSocket(final Socket decoratee)
	{
		super(decoratee);
	}



	/**
	 * Called by {@link InterruptibleRMISocketInputStream} and
	 * {@link InterruptibleRMISocketOutputStream} before the thread enters an RMI IO operation.
	 */
	@Override
	void ioStarting()
	{
		if (Thread.currentThread() instanceof InterruptibleRMIThread)
		{
			((InterruptibleRMIThread) Thread.currentThread()).registerSocketInIO(this);
		}
	}



	/**
	 * Called by {@link InterruptibleRMISocketInputStream} and
	 * {@link InterruptibleRMISocketOutputStream} after the thread exits an RMI IO operation.
	 */
	@Override
	void ioEnding()
	{
		if (Thread.currentThread() instanceof InterruptibleRMIThread)
		{
			((InterruptibleRMIThread) Thread.currentThread()).unregisterSocketInIO();
		}
	}

}
