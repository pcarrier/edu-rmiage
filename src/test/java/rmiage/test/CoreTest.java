package rmiage.test;
import rmiage.framework.core.Leaf;
import rmiage.framework.core.Node;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for framework.core.
 */
public class CoreTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CoreTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CoreTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testNodeCtor_notnull()
    {
    	Node n = new Node("N1");
    	assertNotNull(n);
    }
    
    /**
     * Test if the constructor return a not null node object with 
     * a not null leafs list.
     */
    public void testNodeCtor_leafs_notnull()
    {
    	Node n = new Node("N1");
    	assertNotNull(n.leafs());
    }
    
    /**
     * Test if the constructor return a not null node object with 
     * a not null nodes list.
     */
    public void testNodeCtor_nodes_notnull()
    {
    	Node n = new Node("N1");
    	assertNotNull(n.nodes());
    }
 
    /**
     * Test if a null leaf is accepted in the tree.
     */
    public void testNodeAddLeaf_rejectNull(){
    	Node n = new Node("Node1");
    	n.addLeaf(null);
    	assertEquals(0, (n.leafs().size()));
    }
    
    /**
     * Test if a not null leaf is accepted in the tree.
     */
    public void testNodeAddLeaf_AcceptNotNull(){
    	Node n = new Node("N1");
    	n.addLeaf(new Leaf("Feuille1"));
    	assertEquals(1, (n.leafs().size()));
    }
    
    /**
     * Test if a a leaf is accepted in Twice in the same node.
     */
    public void testNodeAddLeaf_AcceptTwice(){
    	Node n = new Node("N1");
    	Leaf f=new Leaf("Feuille1");
    	n.addLeaf(f);
    	n.addLeaf(f);
    	assertEquals(1, (n.leafs().size()));
    }
    
    /**
     * Test if a leaf is well dropped
     */
    public void testNode_DropLeaf(){
    	Node n = new Node("N1");
    	Leaf f=new Leaf("Feuille1");
    	n.addLeaf(f);
    	int  nbDroped=n.dropLeaf("Feuille1");
    	assertEquals(1,nbDroped);
    }
    
    /**
     * Test if a leaf a random number of leafs are well dropped
     */
    public void testNode_DropRandomLeaf(){
    	int i;
    	int m= (int)(Math.random()*1000);
    	Node n = new Node("N1");

    	for (i=0; i<m; i++){
    		Leaf f=new Leaf("Feuille");	
    		n.addLeaf(f);
    	}
    	//Original size
    	int base=n.leafs().size();
    	//Size after deletion 
    	int  nbDroped=n.dropLeaf("Feuille");
    	// Is it OK?
    	assertEquals(0,base-nbDroped);
    }
    
    
    ///////////////////////////////////////////////////////////
    
    /**
     * Test if a null node is accepted in the tree.
     */
    public void testNodeAddNode_rejectNull(){
    	Node n = new Node("Node1");
    	n.addNode(null);
    	assertEquals(0, (n.nodes().size()));
    }
    
    /**
     * Test if a not null node is accepted in the tree.
     */
    public void testNodeAddNode_AcceptNotNull(){
    	Node n = new Node("N1");
    	n.addNode(new Node("Feuille1"));
    	assertEquals(1, (n.nodes().size()));
    }
    
    /**
     * Test if a node is accepted in Twice in the same node.
     */
    public void testNodeAddNode_AcceptTwice(){
    	Node n = new Node("N1");
    	Node f=new Node("Feuille1");
    	n.addNode(f);
    	n.addNode(f);
    	assertEquals(1, (n.nodes().size()));
    }
    
    /**
     * Test if a Node is well dropped
     */
    public void testNode_DropNodeByRepr(){
    	Node n = new Node("N1");
    	Node f=new Node("Node1");
    	n.addNode(f);
    	int  nbDroped=n.dropFoNodes("Node1");
    	afficherArbre(n, "");
    	assertEquals(1,nbDroped);
    }
    
    /**
     * Test if a leaf a random number of first order nodes 
     * are well dropped
     */
    
    public void testNode_DropRandomNode_firstOrder(){
    	int i;
    	int m= (int)(Math.random()*1000);
    	Node n = new Node("N1");

    	for (i=0; i<m; i++){
    		Node f=new Node("Feuille");	
    		n.addNode(f);
    	}
    	//Original size
    	int base=n.nodes().size();
    	//Size after deletion 
    	int  nbDroped=n.dropFoNodes("Feuille");
    	// Is it OK?
    	assertEquals(0,base-nbDroped);
    }
    
    
    /**
     * Test if a random number of first order nodes 
     * are well dropped all well dropped
     */
    
    public void testNode_DropRandomNode_NOrder(){
    	int i;
    	int m= (int)(1000);
    	Node n = new Node("N1");
    	Node cur =n;
    	for (i=0; i<m; i++){
    		Node f=new Node("Feuille"+i);	
    		cur.addNode(f);
    		cur=f;
    	}
    	//afficherArbre(n, "");
    	//Original size
    	int base=m;
    	//System.out.println(base);
    	//Size after deletion 
    	String todrop="Feuille";//+(int)((Math.random())*m);
    	//System.out.println("TO DROP : "+todrop);
    	int  nbDroped=n.dropSubNodes(todrop);
    	//System.out.println(nbDroped);
    	// Is it OK?
    	//afficherArbre(n, "");
    	//System.out.println("Droped "+(nbDroped));
    	assertEquals(0,base-nbDroped);
    }

    public static void afficherArbre(Node root, String prefix){
        System.out.println(prefix+root.getRepr());
        if (root.leafs().size()>0)
        {
            System.out.println("Feuilles de "+root.getRepr()+":");
        for(Leaf x : root.leafs()){
                System.out.println(prefix+x.getRepr());
            }
        }

        if (root.nodes().size()>0)
        {
            System.out.println("Noeuds de "+root.getRepr()+":");
        for(Node x : root.nodes()){
                afficherArbre((Node)x, prefix+"     ");
            }
        }

    }
}
