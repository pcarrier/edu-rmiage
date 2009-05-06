package rmiage.server.modules.demo.commontext;

import java.rmi.RemoteException;
import rmiage.server.controller.SessionController;
import rmiage.server.modules.NavigTreeNode;
import rmiage.server.modules.PanelDescriptor;
import rmiage.server.modules.TreeModel;
import rmiage.server.modules.TreeModule;

public class CommonTextModule implements TreeModule {

    private SessionController sc;
    private PanelDescriptor pd;

    public CommonTextModule(SessionController sc) {
        this.sc = sc;
        pd = new PanelDescriptor((Class) CommonTextPanel.class, null);
    }

    public TreeModel getTreeModel() throws RemoteException {
        NavigTreeNode rootNode = new NavigTreeNode("Common text");
        TreeModel model = new TreeModel();
        model.setRootNode(rootNode);
        return model;
    }

    public PanelDescriptor getPanel(rmiage.common.interfaces.NavigTreeNode identifier) throws RemoteException {
        return pd;
    }
}
