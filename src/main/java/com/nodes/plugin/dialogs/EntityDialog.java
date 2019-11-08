package com.nodes.plugin.dialogs;

import com.nodes.plugin.models.Entity;
import com.nodes.plugin.utils.TextUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.lang.model.SourceVersion;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EntityDialog extends JDialog {

    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtClassName;
    private JLabel txtError;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox checkBoxRoom;
    private JCheckBox checkBoxRepository;
    private JCheckBox checkBoxExtendsDomain;

    private DialogListener<Entity> listener;

    private EntityDialog(DialogListener<Entity> listener) {
        this.listener = listener;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setAlwaysOnTop(true);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        onCancel();
                    }
                });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(
                e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setFieldListener();
    }

    private void setFieldListener() {
        txtName.getDocument()
                .addDocumentListener(
                        new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                updateClassName();
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                updateClassName();
                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {
                                updateClassName();
                            }
                        });
    }

    private void updateClassName() {
        txtClassName.setText(TextUtils.caps(txtName.getText()) + "Entity");
    }

    private void onCancel() {
        dispose();
    }

    private void onOK() {
        Entity entity =
                new Entity(
                        txtName.getText(),
                        checkBoxRoom.isSelected(),
                        checkBoxRepository.isSelected(),
                        checkBoxExtendsDomain.isSelected());

        if (!SourceVersion.isIdentifier(entity.getName())
                || SourceVersion.isKeyword(entity.getName())) {
            txtError.setText("Invalid class name");
            return;
        }

        listener.onDialogOk(entity);
        dispose();
    }

    public static EntityDialog create(DialogListener<Entity> listener) {
        EntityDialog dialog = new EntityDialog(listener);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - dialog.getWidth()) / 2;
        int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.pack();
        dialog.setVisible(true);
        return dialog;
    }
}
