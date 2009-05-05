package rmiage.server.modules.demo.fixedtree;

import java.rmi.RemoteException;

import rmiage.common.interfaces.PanelDescriptor;
import rmiage.server.controller.SessionController;
import rmiage.server.modules.BasicModule;
import rmiage.server.modules.TreeModel;
import rmiage.server.modules.TreeModule;
import rmiage.server.modules.NavigTreeNode;

public class FixedTreeModule extends BasicModule implements TreeModule {

	protected NavigTreeNode root;
	//protected FixedTreePanelDescriptor pannelDescriptor;
	
    public FixedTreeModule(SessionController sc) throws RemoteException {
        super(sc);
        init();        
    }
    
    public void init() throws RemoteException{
    	root = new NavigTreeNode("root");
        
        for (int i = 0; i < 3; i++) {
        	 NavigTreeNode tmp =new NavigTreeNode("Child" + i);
            for (int j = 0; j < 3; j++) {
            	NavigTreeNode tmp2 =new NavigTreeNode("SubChild" + j);
            	tmp.addNode(tmp2);
            }
        	 root.addNode(tmp);
        }
    }

    public rmiage.common.interfaces.TreeModel getTreeModel() throws RemoteException {
        TreeModel ret = new rmiage.server.modules.TreeModel();
        ret.setRootNode(root);
        return ret;
    }

    private int findNodeRank(NavigTreeNode n) throws RemoteException{
    	int i=0;
    	boolean founded = false;
    	for(rmiage.common.interfaces.NavigTreeNode subn : root.getChildNodes()){
    		i++;
    		if(subn.equals(n)){
    			break; 
    		}
    	}
    	return i;
    }
    public PanelDescriptor getPanel(rmiage.common.interfaces.NavigTreeNode node) throws RemoteException {
    	//Create initial data from the node for the descriptor.
    	Object initalPanelData=findNodeRank((NavigTreeNode) node);
        
    	FixedTreePanelDescriptor pannelDescriptor = new FixedTreePanelDescriptor(initalPanelData);
        return pannelDescriptor;
    }
}