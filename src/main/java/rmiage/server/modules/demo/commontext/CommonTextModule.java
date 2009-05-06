package rmiage.server.modules.demo.commontext;

import java.rmi.RemoteException;

import java.util.List;
import rmiage.common.messages.ClientMessage;
import rmiage.server.controller.SessionController;
import rmiage.server.modules.NavigTreeNode;
import rmiage.server.modules.PanelDescriptor;
import rmiage.server.modules.TreeModel;
import rmiage.server.modules.TreeModule;

public class CommonTextModule implements TreeModule {

    private SessionController sc;
    private PanelDescriptor pd;

    public CommonTextModule(SessionController sc) throws RemoteException {
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

    public void processMessage(ClientMessage msg) {
        if (((String) msg.information[0]).startsWith("NewCommonText")) {
            List<SessionController> sessions =
                    SessionController.getCurrentSessions();
            for (SessionController s : sessions) {
                if (s != sc) {
                    try {
                        System.out.println((String)msg.information[1]);
                        s.sendMessageToPanel((String)msg.information[1]);
                    } catch (RemoteException ex) {
                        throw new InternalError();
                    }
                }
            }
        }

    }
}
