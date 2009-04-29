package rmiage.test;
import java.util.ArrayList;

import rmiage.framework.core.Leaf;
import rmiage.framework.data.Container;
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
    
    ////////////////////////NODES///////////////////////////////////
    /**
     * Test if the constructor return a not null node object with 
     * a not null leafs list.
     */
    public void testNodeCtor_leafs_ReturnNotnull()
    {
    	Container n = new Container("N1");
    	assertNotNull(n.leafs());
    }
    
    
    /**
     * Test if a null leaf is accepted in the tree.
     */
    public void testNodeAddLeaf_rejectNull(){
    	Container n = new Container("Node1");
    	n.addLeaf(null);
    	assertEquals(0, (n.leafs().size()));
    }
    
    /**
     * Test if a not null leaf is accepted in the tree.
     */
    public void testNodeAddLeaf_AcceptNotNull(){
    	Container n = new Container("N1");
    	n.addLeaf(new Leaf("Feuille1"));
    	assertEquals(1, (n.leafs().size()));
    }
    
    /**
     * Test if the same leaf reference is accepted in the same node.
     */
    public void testNodeAddLeaf_AcceptTwice(){
    	Container n = new Container("N1");
    	Leaf f=new Leaf("Feuille1");
    	n.addLeaf(f);
    	n.addLeaf(f);
    	assertEquals(1, (n.leafs().size()));
    }

    
    /**
     * Test if a leaf is well dropped.
     */
    public void testDropLeaf(){
    	Container n = new Container("N1");
    	Leaf f=new Leaf("Feuille1");
    	n.addLeaf(f);
    	n.dropLeaf(f);
    	assertEquals(0, n.leafs().size());
    }
    
    /**
     * Test if all first order leafs are well dropped.
     */
    public void testDropLeafs(){
    	Container n = new Container("N1");
    	int m= (int)(Math.random()*1000);
    	for(int i=0;i<m;i++){
    		Leaf f=new Leaf("Feuille"+i);
    		n.addLeaf(f);
    	}
    	n.dropLeafs();
    	assertEquals(0, n.leafs().size());
    }
    
    
    /**
     * Test if a node is well dropped.
     */
    public void testDropNode(){
    	Container n = new Container("N1");
    	Container f=new Container("Feuille1");
    	n.addNode(f);
    	n.dropNode(f);
    	assertEquals(0, n.nodes().size());
    }
    
    /**
     * Test if all subnodes are well dropped.
     */
    public void testDropSubNodes(){
    	Container n = new Container("N1");
    	int m= (int)(Math.random()*1000);
    	for(int i=0;i<m;i++){
    		Container f=new Container("N"+i);
    		n.addNode(f);
    	}
    	n.dropSubNodes();
    	assertEquals(0, n.nodes().size());
    }
    
    
    /**
     * Test if all subnodes are well dropped and don't have any son
     */
    public void testDropSubNodes_multipleOrder_son(){
    	Container n = new Container("N");
    	Container cur = n;
    	ArrayList<Container> tmp = new ArrayList<Container>();
    	int m= (int)(Math.random()*15);
    	for(int i=0;i<m;i++){
    		Container f=new Container("N"+i);
    		cur.addNode(f);
    		tmp.add(f);
    		cur=f;
    	}
    	//afficherArbre(n, "");
    	n.dropSubNodes();

    	//Check if all subnodes are cleaned.
    	//Each node should not have any son
    	for(Container node:tmp){
    		assertEquals(0, node.nodes().size());
    	}
    }

    /**
     * Test if all subnodes are well dropped and don't have any leaf
     */
    public void testDropSubNodes_multipleOrder_leafs(){
    	Container n = new Container("N");
    	Container cur = n;
    	ArrayList<Container> tmp = new ArrayList<Container>();
    	int m= (int)(Math.random()*15);
    	for(int i=0;i<m;i++){
    		Container f=new Container("N"+i);
    		cur.addNode(f);
    		tmp.add(f);
    		cur=f;
    	}
    	//afficherArbre(n, "");
    	n.dropSubNodes();

    	//Check if all subnodes are cleaned.
    	//Each node should not have any leaf
    	for(Container node:tmp){
    		assertEquals(0, node.leafs().size());
    	}
    }

    
    
    /**
     * Test if a null node is accepted in the tree.
     */
    public void testNodeAddNode_rejectNull(){
    	Container n = new Container("Node1");
    	n.addNode(null);
    	//We shouldn't have any node
    	assertEquals(0, (n.nodes().size()));
    }
    
    /**
     * Test if a not null node is accepted in the tree.
     */
    public void testNodeAddNode_AcceptNotNull(){
    	Container n = new Container("N1");
    	n.addNode(new Container("N2"));
    	//We should have one node
    	assertEquals(1, (n.nodes().size()));
    }
    
    /**
     * Test if a node is accepted in Twice in the same node.
     */
    public void testNodeAddNode_AcceptTwice(){
    	Container n = new Container("N1");
    	Container f=new Container("N2");
    	n.addNode(f);
    	n.addNode(f);
    	//We should just have one node
    	assertEquals(1, (n.nodes().size()));
    }
    
    /**
     * Test if nodes of first order are well retrieved in a not strict research
     */
    public void testFindFOSubNodes_notstrict(){
    	Container n = new Container("N1");
    	int m= (int)(Math.random()*500);
    	for (int i=0;i<m;i++){
    		n.addNode(new Container("F"+m));
    	}
    	//We should find 'm' nodes
    	assertEquals(m, (n.findSubNodes("F", false).size()));
    }
    
    /**
     * Test if nodes are well retrieved in a not strict research
     */
    public void testFindSubNodes_notstrict(){ 
    	Container n = new Container("N");
    	Container cur = n;
    	ArrayList<Container> tmp = new ArrayList<Container>();
    	int m= (int)(Math.random()*500);
    	for(int i=0;i<m;i++){
    		Container f=new Container("F"+i);
    		cur.addNode(f);
    		tmp.add(f);
    		cur=f;
    	}
    	
    	assertEquals(m, (n.findSubNodes("F", false).size()));
    }
    

    /**
     * Test if nodes of first order are well retrieved in a strict research
     */
    public void testFindFOSubNodes_strict(){
    	Container n = new Container("N1");
    	int m= (int)(Math.random()*500);
    	for (int i=0;i<m;i++){
    		n.addNode(new Container("F"+i));
    	}
    	//We should find 'm' node
    	for (int i=0;i<m;i++){
    		assertEquals(1, (n.findSubNodes("F"+i, true).size()));
    	}
    }
    
    
    /**
     * Test if nodes are well retrieved in a strict research
     */
    public void testFindSubNodes_strict(){ 
    	Container n = new Container("N");
    	Container cur = n;
    	ArrayList<Container> tmp = new ArrayList<Container>();
    	int m= (int)(Math.random()*500);
    	for(int i=0;i<m;i++){
    		Container f=new Container("F"+i);
    		cur.addNode(f);
    		tmp.add(f);
    		cur=f;
    	}
    	//We should find 1 node each time
    	for (int i=0;i<m;i++){
    		assertEquals(1, (n.findSubNodes("F"+i, true).size()));
    	}
    }
    
   
    /**
     * Strict research of first order leafs
     */
    public void testFindFOLeafs_strict(){
    	Container n = new Container("N1");
    	int m= (int)(Math.random()*500);
    	//int m= 10000;
    	for (int i=0;i<m;i++){
    		n.addLeaf(new Leaf("F"+i));
    	}
    	//We should find 1 leaf each time
    	for (int i=0;i<m;i++){
    		assertEquals(1, (n.findFoLeaf("F"+i, true).size()));
    	}
    }
    /**
     * Not strict research of first order leafs
     */
    public void testFindFOLeafs_notstrict(){
    	Container n = new Container("N1");
    	int m= (int)(Math.random()*500);
    	//int m= 10000;
    	for (int i=0;i<m;i++){
    		n.addLeaf(new Leaf("F"+i));
    	}
    	//We should find 'm' leaf
    	for (int i=0;i<m;i++){
    		assertEquals(m, (n.findFoLeaf("F", false).size()));
    	}
    }
    
    
    /**
     * Strict research of all leafs
     */
    public void testFindSubLeafs_strict(){ 
    	Container n = new Container("N");
    	Container cur = n;
    	int m= (int)(Math.random()*500);
    	for(int i=0;i<m;i++){
    		Container f=new Container("N"+i);
    		Leaf l=new Leaf("F"+i);
    		cur.addNode(f);
    		cur.addLeaf(l);
    		cur=f;
    	}
    	//We should find 1 leaf each time
    	for (int i=0;i<m;i++){
    		assertEquals(1, (n.findSubsLeaf("F"+i, true).size()));
    	}
    }
    
    /**
     * Strict research of all leafs
     */
    public void testFindSubLeafs_notstrict(){ 
    	Container n = new Container("N");
    	Container cur = n;
    	int m= (int)(Math.random()*500);
    	for(int i=0;i<m;i++){
    		Container f=new Container("N"+i);
    		Leaf l=new Leaf("F"+i);
    		cur.addNode(f);
    		cur.addLeaf(l);
    		cur=f;
    	}
    	//We should find 'm' leafs in the tree
    	for (int i=0;i<m;i++){
    		assertEquals(m, (n.findSubsLeaf("F", false).size()));
    	}
    	
    }
    ////////////////////////UTILS///////////////////////////////////
    public static void afficherArbre(Container root, String prefix){
        //System.out.println(prefix+root.getRepr());
        if (root.leafs().size()>0)
        {
            System.out.println("Feuilles de "+root.getRepr()+":");
        for(Leaf x : root.leafs()){
                System.out.println(prefix+x.getRepr());
            }
        }

        if (root.nodes().size()>0)
        {
            //System.out.println("Noeuds de "+root.getRepr()+":");
        for(Container x : root.nodes()){
                afficherArbre((Container)x, prefix+"     ");
            }
        }

    }
}
