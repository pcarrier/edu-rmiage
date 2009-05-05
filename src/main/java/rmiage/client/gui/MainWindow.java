package rmiage.client.gui;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import rmiage.client.controller.NetworkManager;
import rmiage.common.interfaces.TreeModel;

public class MainWindow extends javax.swing.JFrame {

    /** Creates new form MainWindow */
    protected MainWindow() {
        super();
        initComponents();
    }

    public MainWindow(NetworkManager nm) {
        this();
        this.networkManager = nm;
    }

    public javax.swing.tree.TreeModel getSwingTreeModel(TreeModel tm){
    	DefaultMutableTreeNode top =  new DefaultMutableTreeNode(tm.getRootNode().getName());
    	System.err.println("root"+top);
    	DefaultTreeModel arbreModele = new DefaultTreeModel(top);
		return arbreModele;
    	
    }
    public void updateTree(TreeModel tm) {
    	System.err.println("UpdateTree");
        this.navigTree.setModel(getSwingTreeModel(tm));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        navigScrollPane = new javax.swing.JScrollPane();
        navigTree = new javax.swing.JTree();
        mainPanel = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        searchIcon = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        disconnectButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RMIAGE");

        splitPane.setDividerLocation(150);

        navigScrollPane.setViewportView(navigTree);

        splitPane.setLeftComponent(navigScrollPane);

        mainPanel.setLayout(new java.awt.BorderLayout());
        splitPane.setRightComponent(mainPanel);

        getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);

        topPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rmiage/client/resources/search.png"))); // NOI18N
        topPanel.add(searchIcon);

        searchField.setPreferredSize(new java.awt.Dimension(200, 28));
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        topPanel.add(searchField);

        disconnectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rmiage/client/resources/disconnect.png"))); // NOI18N
        disconnectButton.setBorder(null);
        disconnectButton.setBorderPainted(false);
        disconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectButtonActionPerformed(evt);
            }
        });
        topPanel.add(disconnectButton);

        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_searchFieldActionPerformed

    private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectButtonActionPerformed
        networkManager.close();
    }//GEN-LAST:event_disconnectButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    private NetworkManager networkManager;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton disconnectButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane navigScrollPane;
    private javax.swing.JTree navigTree;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel searchIcon;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
