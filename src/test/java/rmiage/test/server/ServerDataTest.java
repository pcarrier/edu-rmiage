package rmiage.test.server;

import rmiage.server.data.User;
import rmiage.server.data.UserBasic;
import rmiage.server.data.UserGroupBasic;
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
    	UserGroupBasic<UserBasic> g = new UserGroupBasic<UserBasic>("base");
    	assertNotNull(g);
    }
    
    /**
     * Simple Test
     * Test the userGroup Constructor getName
     */
    public void testUserGroupCtor_getName(){
    	UserGroupBasic<UserBasic> g = new UserGroupBasic<UserBasic>("base");
    	assertEquals("base", g.getName());
    }
    
    public void testUserGroupBasi_AddUserBasic(){
    	UserGroupBasic<UserBasic> g = new UserGroupBasic<UserBasic>("base");
    	UserBasic u = new UserBasic("jc");
    	g.addContent(u);
    }
    
    
}
