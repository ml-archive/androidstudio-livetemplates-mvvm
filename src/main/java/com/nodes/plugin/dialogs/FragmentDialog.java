package com.nodes.plugin.dialogs;

import com.nodes.plugin.models.Fragment;
import com.nodes.plugin.utils.TextUtils;

import javax.lang.model.SourceVersion;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FragmentDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtName;
    private JTextField txtClassName;
    private JLabel txtError;
    private JTextField txtLayout;

    private DialogListener<Fragment> listener;

    private FragmentDialog(DialogListener<Fragment> listener) {

        this.listener = listener;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setAlwaysOnTop(true);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        setFieldListener();

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(
                e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
    }

    private void setFieldListener() {
        txtName.getDocument().addDocumentListener(new DocumentListener() {
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
        txtClassName.setText(TextUtils.caps(txtName.getText()) + "Fragment");
        txtLayout.setText("fragment_" + txtName.getText().toLowerCase());
    }

    private void onOK() {

        Fragment a = new Fragment(txtName.getText());

        if (!SourceVersion.isIdentifier(a.getName()) || SourceVersion.isKeyword(a.getName())) {
            txtError.setText("Invalid class name");
            return;
        }

        listener.onDialogOk(a);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static FragmentDialog create(DialogListener<Fragment> listener) {
        FragmentDialog dialog = new FragmentDialog(listener);
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
