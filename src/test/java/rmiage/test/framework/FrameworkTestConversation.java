package rmiage.test.framework;

import rmiage.framework.data.Conversation;
import rmiage.framework.data.Message;
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
    	assertEquals(1,c.Contents());
    
    }
}
