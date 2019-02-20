package com.nodes.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.nodes.plugin.dialogs.ViewModelDialog
import com.nodes.plugin.generators.ViewModelGenerator
import com.nodes.plugin.generators.ViewStateGenerator
import com.nodes.plugin.models.ViewModel

class ViewModelAction : BaseAction<ViewModel>() {

    override lateinit var modelObject: ViewModel

    override fun actionPerformed(actionEvent: AnActionEvent) {
        super.actionPerformed(actionEvent)
        ViewModelDialog.create(this)
    }

    override fun onDialogOk(data: ViewModel) {
        super.onDialogOk(data)
        ViewModelGenerator().generateFiles(directory, modelObject)
        ViewStateGenerator().generateFiles(directory, modelObject)
    }

}