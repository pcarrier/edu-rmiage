package rmiage.test.server;


import rmiage.data.UserBasic;
import rmiage.data.UserGroupBasic;
import rmiage.server.data.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ServerDataBasicTest extends TestCase {

	public ServerDataBasicTest(String name) {
		super(name);
	}
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ServerDataBasicTest.class );
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
    public void testUserGroupBasicCtor_getName(){
    	UserGroupBasic<UserBasic> g = new UserGroupBasic<UserBasic>("base");
    	assertEquals("base", g.getName());
    }
    /**
     * Test if a UserGroupBasic accept a UserBasic
     */
    public void testUserGroupBasic_AddUserBasic(){
    	UserGroupBasic<UserBasic> g = new UserGroupBasic<UserBasic>("base");
    	UserBasic u = new UserBasic("jc");
    	g.addContent(u);
    	assertEquals(1, g.Contents().size());
    }
    /**
     *Test if a UserGroupBasic accept a subclass of UserBasic 
     */
    public void testUserGroupBasic_AddUserExtended(){
    	UserGroupBasic<UserBasic> g = new UserGroupBasic<UserBasic>("base");
    	User u = new User("jc", "password");
    	g.addContent(u);
    	assertEquals(1, g.Contents().size());
    }
    
}
