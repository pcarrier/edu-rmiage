package rmiage.server.modules.demo.commontext;

import java.io.Serializable;
import java.rmi.RemoteException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import rmiage.common.interfaces.Panel;
import rmiage.common.interfaces.SessionController;

public class CommonTextPanel extends Panel {

    private SessionController sc;

    /** Creates new form CommonTextPanel */
    public CommonTextPanel() {
        initComponents();
        jTextPane1.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                textUpdated();
            }

            public void removeUpdate(DocumentEvent e) {
                textUpdated();
            }

            public void changedUpdate(DocumentEvent e) {
                textUpdated();
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
        add(jLabel1);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        add(jScrollPane2);

        jScrollPane1.setViewportView(jTextPane1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void textUpdated() {
        Serializable[] content = new Serializable[2];
        content[0] = "NewCommonText";
        content[1] = jTextPane1.getText();
        try {
            sc.sendMessageToModules(content);
        } catch (RemoteException ex) {
            throw new InternalError();
        }
    }

    @Override
    public void close() {
    }

    @Override
    public void initialize(Serializable initialData, SessionController sc) {
        this.sc = sc;
    }

    @Override
    public void receiveMessage(Serializable message) {
        jTextArea1.setText((String)message);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
