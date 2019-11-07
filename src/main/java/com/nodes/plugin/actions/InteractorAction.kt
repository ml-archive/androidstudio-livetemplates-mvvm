package com.nodes.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.nodes.plugin.dialogs.InteractorDialog
import com.nodes.plugin.generators.InteractorGenerator
import com.nodes.plugin.models.Interactor

class InteractorAction : BaseAction<Interactor>() {

    override lateinit var modelObject: Interactor

    override fun actionPerformed(actionEvent: AnActionEvent) {
        super.actionPerformed(actionEvent)
        InteractorDialog.create(this)
    }

    override fun onDialogOk(data: Interactor) {
        super.onDialogOk(data)
        InteractorGenerator().generateFiles(directory, modelObject)
    }
}
