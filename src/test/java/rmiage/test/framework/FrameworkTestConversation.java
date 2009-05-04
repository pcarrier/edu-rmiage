//package rmiage.test.framework;
//
//import rmiage.data.bidonConversation;
//import rmiage.data.bidonMessage;
//import rmiage.data.bidonUser;
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//public class FrameworkTestConversation extends TestCase {
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public FrameworkTestConversation( String testName )
//    {
//        super( testName );
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( FrameworkTestConversation.class );
//    }
//
//    public void testConversationCtor(){
//    	bidonConversation c = new bidonConversation("HELLO");
//    	assertNotNull(c);
//
//    }
//    public void testConversationSetGetName(){
//    	bidonConversation c = new bidonConversation();
//    	c.setName("HELLO");
//    	assertEquals("HELLO",c.getName());
//
//    }
//
//    public void testConversationAddMessage(){
//    	bidonConversation c = new bidonConversation();
//    	c.setName("HELLO");
//    	bidonMessage m = new bidonMessage("First Message","Hello World !");
//    	c.addContent(m);
//    	assertEquals(1,c.Contents().size());
//    }
//
//    public void testUserAddConversation(){
//    	bidonUser u = new bidonUser("jc","pass");
//    	u.getConversations().addContent(new bidonConversation());
//    	assertEquals(1, u.getConversations().Contents().size());
//    }
//
//    public void testUserAddNewMessage(){
//    	bidonUser u = new bidonUser("jc","pass");
//    	bidonConversation c = new bidonConversation();
//    	c.setName("HELLO");
//    	bidonMessage m = new bidonMessage("First Message","Hello World !");
//    	c.addContent(m);
//    	u.addConversation(c);
//
//    }
//
//    /**
//     *
//     * Test the search on the message subject
//     */
//    public void testFind_subject(){
//    	bidonUser u = new bidonUser("jc","pass");
//    	bidonConversation c = new bidonConversation();
//    	c.setName("HELLO");
//    	bidonMessage m = new bidonMessage("First Message","Hello World !");
//    	c.addContent(m);
//    	u.addConversation(c);
//
//    	assertEquals(1,c.find("message", false).Contents().size());
//    }
//    /**
//     * Test the search on the message corpus
//     *
//     */
//    public void testFind_corpus(){
//    	bidonUser u = new bidonUser("jc","pass");
//    	bidonConversation c = new bidonConversation();
//    	c.setName("HELLO");
//    	bidonMessage m = new bidonMessage("First Message","Hello World !");
//    	c.addContent(m);
//    	u.addConversation(c);
//
//    	assertEquals(1,c.find("world", false).Contents().size());
//    }
//}
