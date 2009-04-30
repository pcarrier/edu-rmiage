package rmiage.test.server;

import rmiage.server.data.UserGroup;
import rmiage.test.framework.FrameworkDataTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ServerDataTest extends TestCase {

	public ServerDataTest(String name) {
		super(name);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ServerDataTest.class );
    }
    
    /**
     * Test the userGroup Constructor
     */
    public void testUserGroupCtor(){
    	UserGroup g = new UserGroup("base");
    	assertNotNull(g);
    }
    
    /**
     * Test the userGroup Constructor getName
     */
    public void testUserGroupCtor_getName(){
    	UserGroup g = new UserGroup("base");
    	assertEquals("base", g.getName());
    }	
}
