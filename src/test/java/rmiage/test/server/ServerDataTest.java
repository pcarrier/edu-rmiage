//package rmiage.test.server;
//
//import rmiage.data.bidonContainer;
//import rmiage.data.bidonContent;
//import rmiage.data.bidonUserBasic;
//import rmiage.data.bidonUserGroupBasic;
//import rmiage.data.bidonUser;
//import rmiage.data.bidonUserGroup;
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//public class ServerDataTest extends TestCase {
//
//	public ServerDataTest(String name) {
//		super(name);
//	}
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( ServerDataTest.class );
//    }
//
//
//	/**
//     * Test the userGroup Constructor
//     */
//    public void testUserGroupCtor(){
//    	bidonUserGroup g = new bidonUserGroup("base");
//    	assertNotNull(g);
//    }
//
//    /**
//     * Simple Test
//     * Test the userGroup Constructor getName
//     */
//    public void testUserGroupBasicCtor_getName(){
//    	bidonUserGroup g = new bidonUserGroup("base");
//    	assertEquals("base", g.getName());
//    }
//    /**
//     * Test if a bidonUserGroupBasic accept a bidonUserBasic
//     */
//    public void testUserGroupBasic_AddUserBasic(){
//
//    	bidonUserGroupBasic<bidonUserBasic> g = new bidonUserGroupBasic<bidonUserBasic>("base");
//    	bidonUserBasic u = new bidonUserBasic("jc");
//    	assertEquals(true, g.addContent(u));
//
//    }
//    /**
//     *Test if a bidonUserGroupBasic accept a subclass of bidonUserBasic
//     */
//    public void testUserGroupBasic_AddUser(){
//    	bidonUserGroupBasic<bidonUser> g = new bidonUserGroupBasic<bidonUser>("base");
//    	bidonUser u = new bidonUser("jc", "password");
//    	g.addContent(u);
//    	assertEquals(1, g.Contents().size());
//    }
//
//    /**
//     *Test if a bidonUserGroup accept a subclass of bidonUserBasic
//     */
//    public void testUserGroup_AddUser(){
//    	bidonUserGroup g = new bidonUserGroup("base");
//    	bidonUserBasic u = new bidonUserBasic("jc");
//    	g.addContent(u);
//    	assertEquals(1, g.Contents().size());
//    }
//
//
//    public void testFind(){
//    	bidonUserGroup g = new bidonUserGroup("base");
//    	bidonUser u = new bidonUser("jc", "password");
//    	g.addContent(u);
//    	bidonContainer<bidonContent> result =g.find("jc", true);
//    	assertEquals(1,result.Contents().size());
//    }
//
//    public void testFind_2(){
//    	bidonUserGroup g = new bidonUserGroup("base");
//    	bidonUser u1 = new bidonUser("jc", "password");
//    	bidonUser u2 = new bidonUser("JC2", "password");
//    	g.addContent(u1);
//    	g.addContent(u2);
//    	bidonContainer<bidonContent> result =g.find("jc", false);
//    	assertEquals(2,result.Contents().size());
//    }
//
//    public void testFind_3(){
//    	int m = (int) (Math.random()*500);
//    	int tofind = (int) (Math.random()*m);
//    	tofind = (tofind>0)?tofind-1:tofind;
//
//    	int i=0;
//    	bidonUserGroup g = new bidonUserGroup("base");
//
//    	for(i=0;i<m;i++){
//    		bidonUser u = new bidonUser("jc"+i, "password");
//        	g.addContent(u);
//    	}
//    	//System.out.println(m+" "+i);
//    	bidonContainer<bidonContent> result =g.find("jc"+tofind, true);
//    	assertEquals(1,result.Contents().size());
//    }
//
//    public void testFind_4(){
//    	int m = (int) (Math.random()*500);
//    	int i=0;
//    	bidonUserGroup g = new bidonUserGroup("base");
//
//    	for(i=0;i<m;i++){
//    		bidonUser u = new bidonUser("jc"+i, "password");
//        	g.addContent(u);
//    	}
//
//    	bidonContainer<bidonContent> result =g.find("jc", false);
//    	assertEquals(m,result.Contents().size());
//    }
//
//    public void testFind_5(){
//    	int m = (int) (Math.random()*500);
//    	int tofind = (int) (Math.random()*m);
//    	tofind = (tofind>0)?tofind-1:tofind;
//
//    	int i=0;
//    	bidonUserGroup g = new bidonUserGroup("root");
//    	bidonUserGroup cur=g;
//    	for(i=0;i<m;i++){
//    		bidonUserGroup tmp=new bidonUserGroup("group"+i);
//    		bidonUser u = new bidonUser("jc"+i, "password");
//        	cur.addContent(u);
//        	cur.addContent(tmp);
//        	cur=tmp;
//    	}
//    	bidonContainer<bidonContent> result =g.find("jc"+tofind, true);
//    	assertEquals(1,result.Contents().size());
//    }
//
//    public void testFind_6(){
//    	int m = (int) (Math.random()*500);
//    	int tofind = (int) (Math.random()*m);
//    	tofind = (tofind>0)?tofind-1:tofind;
//
//    	int i=0;
//    	bidonUserGroup g = new bidonUserGroup("root");
//    	bidonUserGroup cur=g;
//    	for(i=0;i<m;i++){
//    		bidonUserGroup tmp=new bidonUserGroup("group"+i);
//    		bidonUser u = new bidonUser("jc"+i, "password");
//        	cur.addContent(u);
//        	cur.addContent(tmp);
//        	cur=tmp;
//    	}
//    	bidonContainer<bidonContent> result =g.find("jc", false);
//    	assertEquals(m,result.Contents().size());
//    }
//
//}
