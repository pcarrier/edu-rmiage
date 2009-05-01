package rmiage.test.server;

import rmiage.framework.data.UserBasic;
import rmiage.framework.data.UserGroupBasic;
import rmiage.server.data.User;
import rmiage.server.data.UserGroup;
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

	/**
     * Test the userGroup Constructor
     */
    public void testUserGroupCtor(){
    	UserGroup g = new UserGroup("base");
    	assertNotNull(g);
    }
    
    /**
     * Simple Test
     * Test the userGroup Constructor getName
     */
    public void testUserGroupBasicCtor_getName(){
    	UserGroup g = new UserGroup("base");
    	assertEquals("base", g.getName());
    }
    /**
     * Test if a UserGroupBasic accept a UserBasic
     */
    public void testUserGroupBasic_AddUserBasic(){
    	
    	UserGroupBasic<UserBasic> g = new UserGroupBasic<UserBasic>("base");
    	UserBasic u = new UserBasic("jc");
    	assertEquals(true, g.addContent(u));
    	
    }
    /**
     *Test if a UserGroupBasic accept a subclass of UserBasic 
     */
    public void testUserGroupBasic_AddUser(){
    	UserGroupBasic<User> g = new UserGroupBasic<User>("base");
    	User u = new User("jc", "password");
    	g.addContent(u);
    	assertEquals(1, g.Contents().size());
    }
    
    /**
     *Test if a UserGroup accept a subclass of UserBasic 
     */
    public void testUserGroup_AddUser(){
    	UserGroup g = new UserGroup("base");
    	UserBasic u = new UserBasic("jc");
    	g.addContent(u);
    	assertEquals(1, g.Contents().size());
    }
    
}
