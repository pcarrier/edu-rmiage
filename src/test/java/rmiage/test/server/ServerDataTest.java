package rmiage.test.server;

import rmiage.framework.data.Container;
import rmiage.framework.data.Content;
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
    
    
    public void testFind(){
    	UserGroup g = new UserGroup("base");
    	User u = new User("jc", "password");
    	g.addContent(u);
    	Container<Content> result =g.find("jc", true);
    	assertEquals(1,result.Contents().size());
    }
    
    public void testFind_2(){
    	UserGroup g = new UserGroup("base");
    	User u1 = new User("jc", "password");
    	User u2 = new User("JC2", "password");
    	g.addContent(u1);
    	g.addContent(u2);
    	Container<Content> result =g.find("jc", false);
    	assertEquals(2,result.Contents().size());
    }
    
    public void testFind_3(){
    	int m = (int) (Math.random()*500); 
    	int tofind = (int) (Math.random()*m);
    	tofind = (tofind>0)?tofind-1:tofind;
    	
    	int i=0;
    	UserGroup g = new UserGroup("base");
    	
    	for(i=0;i<m;i++){
    		User u = new User("jc"+i, "password");
        	g.addContent(u);
    	}
    	//System.out.println(m+" "+i);
    	Container<Content> result =g.find("jc"+tofind, true);
    	assertEquals(1,result.Contents().size());
    }
    
    public void testFind_4(){
    	int m = (int) (Math.random()*500); 
    	int i=0;
    	UserGroup g = new UserGroup("base");
    	
    	for(i=0;i<m;i++){
    		User u = new User("jc"+i, "password");
        	g.addContent(u);
    	}
    	
    	Container<Content> result =g.find("jc", false);
    	assertEquals(m,result.Contents().size());
    }
    
    public void testFind_5(){
    	int m = (int) (Math.random()*500); 
    	int tofind = (int) (Math.random()*m);
    	tofind = (tofind>0)?tofind-1:tofind;
    	
    	int i=0;
    	UserGroup g = new UserGroup("root");
    	UserGroup cur=g;
    	for(i=0;i<m;i++){
    		UserGroup tmp=new UserGroup("group"+i);
    		User u = new User("jc"+i, "password");
        	cur.addContent(u);
        	cur.addContent(tmp);
        	cur=tmp;
    	}
    	Container<Content> result =g.find("jc"+tofind, true);
    	assertEquals(1,result.Contents().size());
    }
    
    public void testFind_6(){
    	int m = (int) (Math.random()*500); 
    	int tofind = (int) (Math.random()*m);
    	tofind = (tofind>0)?tofind-1:tofind;
    	
    	int i=0;
    	UserGroup g = new UserGroup("root");
    	UserGroup cur=g;
    	for(i=0;i<m;i++){
    		UserGroup tmp=new UserGroup("group"+i);
    		User u = new User("jc"+i, "password");
        	cur.addContent(u);
        	cur.addContent(tmp);
        	cur=tmp;
    	}
    	Container<Content> result =g.find("jc", false);
    	assertEquals(m,result.Contents().size());
    }

}
