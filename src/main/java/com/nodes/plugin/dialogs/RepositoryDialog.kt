package com.nodes.plugin.dialogs

import com.nodes.plugin.models.Repository
import org.apache.http.util.TextUtils

import javax.lang.model.SourceVersion
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import java.awt.*
import java.awt.event.KeyEvent
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

class RepositoryDialog private constructor(private val listener: DialogListener<Repository>) : JDialog() {

    private lateinit var contentPane: JPanel
    private lateinit var buttonOK: JButton
    private lateinit var buttonCancel: JButton
    private lateinit var txtName: JTextField
    private lateinit var txtClassName: JTextField
    private lateinit var txtError: JLabel
    private lateinit var txtType: JTextField

    init {

        setContentPane(contentPane)
        isModal = true
        getRootPane().defaultButton = buttonOK
        isAlwaysOnTop = true

        buttonOK.addActionListener { onOK() }
        buttonCancel.addActionListener { onCancel() }

        setFieldListener()

        // call onCancel() when cross is clicked
        defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                onCancel()
            }
        })

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(
            { onCancel() },
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        )
    }

    private fun setFieldListener() {
        txtName.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) {
                updateClassName()
            }

            override fun removeUpdate(e: DocumentEvent) {
                updateClassName()
            }

            override fun changedUpdate(e: DocumentEvent) {
                updateClassName()
            }
        })
    }

    private fun updateClassName() {
        txtClassName.text = txtName.text.capitalize() + "Repository"
    }

    private fun onOK() {

        val r = Repository(txtName.text, txtType.text)

        if (!SourceVersion.isIdentifier(r.name) || SourceVersion.isKeyword(r.name)) {
            txtError.text = "Invalid class name"
            return
        }
        if (TextUtils.isEmpty(r.returnType)) {
            txtError.text = "Return type cannot be empty"
            return
        }

        listener.onDialogOk(r)
        dispose()
    }

    private fun onCancel() {
        // add your code here if necessary
        dispose()
    }

    companion object {

        fun create(listener: DialogListener<Repository>): RepositoryDialog {
            val dialog = RepositoryDialog(listener)
            val toolkit = Toolkit.getDefaultToolkit()
            val screenSize = toolkit.screenSize
            val x = (screenSize.width - dialog.width) / 2
            val y = (screenSize.height - dialog.height) / 2
            dialog.setLocation(x, y)
            dialog.pack()
            dialog.isVisible = true
            return dialog
        }
    }

}
