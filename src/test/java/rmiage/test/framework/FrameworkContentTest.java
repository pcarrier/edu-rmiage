//package rmiage.test.framework;
//
//import rmiage.data.bidonContainer;
//import rmiage.data.bidonContent;
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
///**
// * Unit test for framework.core.
// */
//public class FrameworkContentTest
//    extends TestCase
//{
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public FrameworkContentTest( String testName )
//    {
//        super( testName );
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( FrameworkContentTest.class );
//    }
//
//    /**
//     * Test if the constructor return a not null bidonContainer<bidonContent> object with
//     * a not null bidonContent list.
//     */
//    public void testContainerCtor_ContentsNotnull()
//    {
//    	bidonContainer<bidonContent> n = new bidonContainer<bidonContent>();
//    	assertNotNull(n.Contents());
//    }
//
//
//    /**
//     * Test if a not bidonContent leaf is accepted in the tree.
//     */
//    public void testAddContent_AcceptNotNull(){
//    	bidonContainer<bidonContent> n = new bidonContainer<bidonContent>();
//    	n.addContent(new bidonContent());
//    	assertEquals(1, (n.Contents().size()));
//    }
//
//    /**
//     * Test if the same bidonContent reference is accepted in the same node.
//     */
//    public void testAddContent_AcceptTwice(){
//    	bidonContainer<bidonContent> n = new bidonContainer<bidonContent>();
//    	bidonContent f=new bidonContent();
//    	n.addContent(f);
//    	n.addContent(f);
//    	assertEquals(1, (n.Contents().size()));
//    }
//
//    /**
//     * Test if the method addContent accept bidonContainer<bidonContent> objects
//     */
//    public void testAddContent_Container(){
//    	bidonContainer<bidonContent> n = new bidonContainer<bidonContent>();
//    	bidonContent f=new bidonContainer<bidonContent>();
//    	n.addContent(f);
//    	assertEquals(1, (n.Contents().size()));
//    }
//
//    /**
//     * Test if a bidonContent is well dropped.
//     */
//    public void testDropContent(){
//    	bidonContainer<bidonContent> n = new bidonContainer<bidonContent>();
//    	bidonContent f=new bidonContent();
//    	n.addContent(f);
//    	n.dropContent(f);
//    	assertEquals(0, n.Contents().size());
//    }
//
//    /**
//     * Test if all first order bidonContent are well dropped.
//     */
//    public void testDropContents(){
//    	bidonContainer<bidonContent> n = new bidonContainer<bidonContent>();
//    	int m= (int)(Math.random()*1000);
//    	for(int i=0;i<m;i++){
//    		bidonContent f=new bidonContent();
//    		n.addContent(f);
//    	}
//    	n.dropContents();
//    	assertEquals(0, n.Contents().size());
//    }
//
//
//    /**
//     * Test if a null node is accepted in the tree.
//     */
//    public void testAddContent_rejectNull(){
//    	bidonContainer<bidonContent> n = new bidonContainer<bidonContent>();
//    	n.addContent(null);
//    	//We shouldn't have any node
//    	assertEquals(0, (n.Contents().size()));
//    }
//
//    /**
//     * Test if the parent is set properly.
//     */
//    public void testAddContent_addParent(){
//    	bidonContainer<bidonContent> n = new bidonContainer<bidonContent>();
//    	bidonContainer<bidonContent> n2 = new bidonContainer<bidonContent>();
//    	n.addContent(n2);
//    	assertEquals(1, (n2.getParents().size()));
//    }
//
//    /**
//     * Test if the parent is dropped properly.
//     */
//    public void testDropContent_dropParent(){
//    	bidonContainer<bidonContent> n = new bidonContainer<bidonContent>();
//    	bidonContainer<bidonContent> n2 = new bidonContainer<bidonContent>();
//    	n.addContent(n2);
//    	n.dropContent(n2);
//    	assertEquals(0, (n2.getParents().size()));
//    }
//
//}
