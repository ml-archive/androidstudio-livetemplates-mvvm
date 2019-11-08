package com.nodes.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.nodes.plugin.dialogs.EntityDialog
import com.nodes.plugin.generators.repository.RepositoryGenerator
import com.nodes.plugin.generators.entity.EntityGenerator
import com.nodes.plugin.generators.repository.LocalDataSourceGenerator
import com.nodes.plugin.generators.repository.RepositoryImplGenerator
import com.nodes.plugin.models.Entity
import com.nodes.plugin.models.Repository

class EntityAction : BaseAction<Entity>() {
    override lateinit var modelObject: Entity

    override fun actionPerformed(actionEvent: AnActionEvent) {
        super.actionPerformed(actionEvent)
        EntityDialog.create(this)
    }

    override fun onDialogOk(data: Entity) {
        super.onDialogOk(data)
        if (modelObject.generateRepository) {
            val repository = Repository(modelObject.name, modelObject.name.capitalize() + "Entity", roomRepository = modelObject.roomAnnotations)
            RepositoryGenerator().generateFiles(directory, repository)
            RepositoryImplGenerator().generateFiles(directory, repository)
            LocalDataSourceGenerator().generateFiles(directory, repository)
        }
        EntityGenerator().generateFiles(directory, modelObject)
    }
}
