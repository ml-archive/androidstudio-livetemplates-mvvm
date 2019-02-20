package com.nodes.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.nodes.plugin.dialogs.ActivityDialog
import com.nodes.plugin.generators.ContractGenerator
import com.nodes.plugin.generators.PresenterGenerator
import com.nodes.plugin.generators.activity.ActivityGenerator
import com.nodes.plugin.generators.activity.ActivityLayoutGenerator
import com.nodes.plugin.models.Activity

class ActivityAction : BaseAction<Activity>() {

    override lateinit var modelObject: Activity

    override fun actionPerformed(actionEvent: AnActionEvent) {
        super.actionPerformed(actionEvent)
        ActivityDialog.create(this)
    }

    override fun onDialogOk(data: Activity) {
        super.onDialogOk(data)
        ActivityGenerator().generateFiles(directory, data)
        ActivityLayoutGenerator().generateFiles(directory, data)
        ContractGenerator().generateFiles(directory, data.name)
        PresenterGenerator().generateFiles(directory, data.name)
    }

}