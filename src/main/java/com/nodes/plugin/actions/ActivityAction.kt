package com.nodes.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.nodes.plugin.dialogs.ActivityDialog
import com.nodes.plugin.generators.BuilderGenerator
import com.nodes.plugin.generators.ViewModelGenerator
import com.nodes.plugin.generators.ViewStateGenerator
import com.nodes.plugin.generators.activity.ActivityGenerator
import com.nodes.plugin.generators.activity.ActivityLayoutGenerator
import com.nodes.plugin.models.Activity
import com.nodes.plugin.models.Naming

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
        ViewModelGenerator().generateFiles(directory, Naming(data.name, "Activity"))
        ViewStateGenerator().generateFiles(directory, Naming(data.name, "Activity"))
        BuilderGenerator().generateFiles(directory, Naming(data.name, "Activity", viewModelName = "${data.name.capitalize()}ActivityViewModel"))
    }

}