package com.nodes.plugin.dialogs;

import com.nodes.plugin.models.Interactor;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.lang.model.SourceVersion;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.http.util.TextUtils;

public class InteractorDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtName;
    private JTextField txtClassName;
    private JLabel txtError;
    private JCheckBox outputCheckbox;
    private JCheckBox inputCheckBox;
    private JTextField txtFieldInputType;
    private JTextField txtFieldOutputType;

    private DialogListener<Interactor> listener;

    private InteractorDialog(DialogListener<Interactor> listener) {

        this.listener = listener;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setAlwaysOnTop(true);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        outputCheckbox.addItemListener(
                itemEvent ->
                        txtFieldOutputType.setEnabled(
                                itemEvent.getStateChange() == ItemEvent.SELECTED));

        inputCheckBox.addItemListener(
                itemEvent ->
                        txtFieldInputType.setEnabled(
                                itemEvent.getStateChange() == ItemEvent.SELECTED));

        setFieldListener();

        // call onCancel() when cross is clicked
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
        txtClassName.setText(
                com.nodes.plugin.utils.TextUtils.caps(txtName.getText()) + "Interactor");
    }

    private void onOK() {

        Interactor i =
                new Interactor(
                        txtName.getText(),
                        outputCheckbox.isSelected() ? txtFieldOutputType.getText() : null,
                        inputCheckBox.isSelected() ? txtFieldInputType.getText() : null
                );

        if (!SourceVersion.isIdentifier(i.getName()) || SourceVersion.isKeyword(i.getName())) {
            txtError.setText("Invalid class name");
            return;
        }
        if (i.getOutputType() != null && TextUtils.isEmpty(i.getOutputType())) {
            txtError.setText("Return type cannot be empty");
            return;
        }
        listener.onDialogOk(i);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static InteractorDialog create(DialogListener<Interactor> listener) {
        InteractorDialog dialog = new InteractorDialog(listener);
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
