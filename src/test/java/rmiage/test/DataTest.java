package rmiage.test;

import rmiage.framework.data.Container;
import rmiage.framework.data.Content;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for framework.core.
 */
public class DataTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DataTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DataTest.class );
    }
    
    ////////////////////////NODES///////////////////////////////////
    /**
     * Test if the constructor return a not null Container object with 
     * a not null Content list.
     */
    public void testContainerCtor_ContentsNotnull()
    {
    	Container n = new Container();
    	assertNotNull(n.Contents());
    }
   
    
    /**
     * Test if a not Content leaf is accepted in the tree.
     */
    public void testAddContent_AcceptNotNull(){
    	Container n = new Container();
    	n.addContent(new Content());
    	assertEquals(1, (n.Contents().size()));
    }
    
    /**
     * Test if the same Content reference is accepted in the same node.
     */
    public void testAddContent_AcceptTwice(){
    	Container n = new Container();
    	Content f=new Content();
    	n.addContent(f);
    	n.addContent(f);
    	assertEquals(1, (n.Contents().size()));
    }

    /**
     * Test if the method addContent accept Container objects
     */
    public void testAddContent_Container(){
    	Container n = new Container();
    	Content f=new Container();
    	n.addContent(f);
    	assertEquals(1, (n.Contents().size()));
    }
    
    /**
     * Test if a Content is well dropped.
     */
    public void testDropContent(){
    	Container n = new Container();
    	Content f=new Content();
    	n.addContent(f);
    	n.dropContent(f);
    	assertEquals(0, n.Contents().size());
    }
    
    /**
     * Test if all first order Content are well dropped.
     */
    public void testDropContents(){
    	Container n = new Container();
    	int m= (int)(Math.random()*1000);
    	for(int i=0;i<m;i++){
    		Content f=new Content();
    		n.addContent(f);
    	}
    	n.dropContents();
    	assertEquals(0, n.Contents().size());
    }
     
    
    /**
     * Test if a null node is accepted in the tree.
     */
    public void testAddContent_rejectNull(){
    	Container n = new Container();
    	n.addContent(null);
    	//We shouldn't have any node
    	assertEquals(0, (n.Contents().size()));
    }
    
    /**
     * Test if the parent is set properly. 
     */
    public void testAddContent_addParent(){
    	Container n = new Container();
    	Container n2 = new Container();
    	n.addContent(n2);
    	assertEquals(1, (n2.getParents().size()));
    }
    
    /**
     * Test if the parent is dropped properly. 
     */
    public void testDropContent_dropParent(){
    	Container n = new Container();
    	Container n2 = new Container();
    	n.addContent(n2);
    	n.dropContent(n2);
    	assertEquals(0, (n2.getParents().size()));
    }

}
