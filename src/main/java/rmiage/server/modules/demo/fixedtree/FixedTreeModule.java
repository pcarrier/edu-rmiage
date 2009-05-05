package rmiage.server.modules.demo.fixedtree;

import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.SessionController;
import rmiage.server.modules.BasicModule;
import rmiage.server.modules.TreeModel;
import rmiage.server.modules.TreeModule;
import rmiage.server.modules.TreeNode;

public class FixedTreeModule extends BasicModule implements TreeModule {

    public FixedTreeModule(SessionController sc) {
        super(sc);
    }

    public TreeModel getTreeModel() {
    	TreeModel  ret = new rmiage.server.modules.TreeModel();
    	TreeNode root= new TreeNode("root");
    	ret.setRootNode(root);
    	for(int i=0; i<10; i++){
    		root.addNode(new TreeNode("Child"+i));
    	}
        return ret;
    }

    public Panel getPanel(rmiage.common.interfaces.TreeNode node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
