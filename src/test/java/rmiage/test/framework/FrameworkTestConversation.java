package rmiage.test.framework;

import rmiage.data.Conversation;
import rmiage.data.Message;
import rmiage.data.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FrameworkTestConversation extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FrameworkTestConversation( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FrameworkTestConversation.class );
    }

    public void testConversationCtor(){
    	Conversation c = new Conversation("HELLO");
    	assertNotNull(c);
    
    }
    public void testConversationSetGetName(){
    	Conversation c = new Conversation();
    	c.setName("HELLO");
    	assertEquals("HELLO",c.getName());
    
    }
    
    public void testConversationAddMessage(){
    	Conversation c = new Conversation();
    	c.setName("HELLO");
    	Message m = new Message("First Message","Hello World !");
    	c.addContent(m);
    	assertEquals(1,c.Contents().size());   
    }
    
    public void testUserAddConversation(){
    	User u = new User("jc","pass");
    	u.getConversations().addContent(new Conversation());
    	assertEquals(1, u.getConversations().Contents().size());
    }
    
    public void testUserAddNewMessage(){
    	User u = new User("jc","pass");
    	Conversation c = new Conversation();
    	c.setName("HELLO");
    	Message m = new Message("First Message","Hello World !");
    	c.addContent(m);
    	u.addConversation(c);
    	
    }
    
    /**
     * 
     * Test the search on the message subject
     */
    public void testFind_subject(){
    	User u = new User("jc","pass");
    	Conversation c = new Conversation();
    	c.setName("HELLO");
    	Message m = new Message("First Message","Hello World !");
    	c.addContent(m);
    	u.addConversation(c);
    	
    	assertEquals(1,c.find("message", false).Contents().size());
    }
    /**
     * Test the search on the message corpus
     * 
     */
    public void testFind_corpus(){
    	User u = new User("jc","pass");
    	Conversation c = new Conversation();
    	c.setName("HELLO");
    	Message m = new Message("First Message","Hello World !");
    	c.addContent(m);
    	u.addConversation(c);
    	
    	assertEquals(1,c.find("world", false).Contents().size());
    }
}
