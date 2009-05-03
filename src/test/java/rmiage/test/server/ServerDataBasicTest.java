package rmiage.test.server;


import rmiage.data.bidonUserBasic;
import rmiage.data.bidonUserGroupBasic;
import rmiage.data.bidonUser;
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
    	bidonUserGroupBasic<bidonUserBasic> g = new bidonUserGroupBasic<bidonUserBasic>("base");
    	assertNotNull(g);
    }
    
    /**
     * Simple Test
     * Test the userGroup Constructor getName
     */
    public void testUserGroupBasicCtor_getName(){
    	bidonUserGroupBasic<bidonUserBasic> g = new bidonUserGroupBasic<bidonUserBasic>("base");
    	assertEquals("base", g.getName());
    }
    /**
     * Test if a bidonUserGroupBasic accept a bidonUserBasic
     */
    public void testUserGroupBasic_AddUserBasic(){
    	bidonUserGroupBasic<bidonUserBasic> g = new bidonUserGroupBasic<bidonUserBasic>("base");
    	bidonUserBasic u = new bidonUserBasic("jc");
    	g.addContent(u);
    	assertEquals(1, g.Contents().size());
    }
    /**
     *Test if a bidonUserGroupBasic accept a subclass of bidonUserBasic
     */
    public void testUserGroupBasic_AddUserExtended(){
    	bidonUserGroupBasic<bidonUserBasic> g = new bidonUserGroupBasic<bidonUserBasic>("base");
    	bidonUser u = new bidonUser("jc", "password");
    	g.addContent(u);
    	assertEquals(1, g.Contents().size());
    }
    
}
