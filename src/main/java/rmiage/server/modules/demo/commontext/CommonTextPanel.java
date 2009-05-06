package rmiage.server.modules.demo.commontext;

import java.io.Serializable;
import java.rmi.RemoteException;
import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.SessionController;

public class CommonTextPanel extends Panel {

    private SessionController sc;

    /** Creates new form CommonTextPanel */
    public CommonTextPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setLayout(new java.awt.BorderLayout());

        jTextPane1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextPane1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTextPane1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextPane1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextPane1PropertyChange
        Serializable[] content = new Serializable[1];
        content[0] = jTextPane1.getText();
        try {
            sc.sendMessageToModules(content);
        } catch (RemoteException ex) {
            throw new InternalError();
        }
    }//GEN-LAST:event_jTextPane1PropertyChange

    @Override
    public void close() {
    }

    @Override
    public void initialize(Serializable initialData, SessionController sc) {
        this.sc = sc;
    }

    @Override
    public void receiveMessage(Serializable message) {
        jTextPane1.setText((String)message);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

}
