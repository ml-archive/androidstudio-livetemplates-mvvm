package com.nodes.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.nodes.plugin.dialogs.RepositoryDialog
import com.nodes.plugin.generators.RepositoryGenerator
import com.nodes.plugin.generators.RepositoryImplGenerator
import com.nodes.plugin.models.Repository

class RepositoryAction : BaseAction<Repository>() {

    override lateinit var modelObject: Repository

    override fun actionPerformed(actionEvent: AnActionEvent) {
        super.actionPerformed(actionEvent)
        RepositoryDialog.create(this)
    }

    override fun onDialogOk(data: Repository) {
        super.onDialogOk(data)
        RepositoryGenerator().generateFiles(directory, modelObject)
        RepositoryImplGenerator().generateFiles(directory, modelObject)
    }
}
