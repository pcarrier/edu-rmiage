package rmiage.server.modules.demo.fixedtree;

import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.SessionController;
import rmiage.server.modules.BasicModule;
import rmiage.server.modules.TreeModule;

public class FixedTreeModule extends BasicModule implements TreeModule {

    public FixedTreeModule(SessionController sc) {
        super(sc);
    }

    public rmiage.common.interfaces.TreeModel getTreeModel() {
        return null;
    }

    public Panel getPanel(rmiage.common.interfaces.TreeNode node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
