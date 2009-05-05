package rmiage.server.modules.demo.fixedtree;

import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.SessionController;
import rmiage.common.interfaces.TreeModel;
import rmiage.common.interfaces.TreeNode;
import rmiage.server.modules.TreeModule;

public class FixedTreeModule implements TreeModule {

    private SessionController sc;

    private FixedTreeModule() {
        super();
    }

    public FixedTreeModule(SessionController sc) {
        this();
        this.sc = sc;
    }

    public TreeModel getTreeModel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Panel getPanel(TreeNode node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
