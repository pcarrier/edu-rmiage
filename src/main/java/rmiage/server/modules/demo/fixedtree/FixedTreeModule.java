package rmiage.server.modules.demo.fixedtree;

import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.SessionController;
import rmiage.common.interfaces.TreeModel;
import rmiage.common.interfaces.TreeNode;
import rmiage.server.modules.BasicModule;
import rmiage.server.modules.TreeModule;

public class FixedTreeModule extends BasicModule implements TreeModule {

    public FixedTreeModule(SessionController sc) {
        super(sc);
    }

    public TreeModel getTreeModel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Panel getPanel(TreeNode node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
