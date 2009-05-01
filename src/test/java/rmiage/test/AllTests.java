package rmiage.test;

import rmiage.test.framework.FrameworkContentTest;
import rmiage.test.server.ServerDataBasicTest;
import rmiage.test.server.ServerDataTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for rmiage.framework.data");
		//$JUnit-BEGIN$
		suite.addTest(FrameworkContentTest.suite());
		suite.addTest(ServerDataBasicTest.suite());
		suite.addTest(ServerDataTest.suite());
		//$JUnit-END$
		return suite;
	}

}
