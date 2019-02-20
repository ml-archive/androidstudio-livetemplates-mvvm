package com.nodes.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.nodes.plugin.dialogs.FragmentDialog
import com.nodes.plugin.generators.ContractGenerator
import com.nodes.plugin.generators.PresenterGenerator
import com.nodes.plugin.generators.fragment.FragmentGenerator
import com.nodes.plugin.generators.fragment.FragmentLayoutGenerator
import com.nodes.plugin.models.Fragment

class FragmentAction : BaseAction<Fragment>() {

    override lateinit var modelObject: Fragment

    override fun actionPerformed(actionEvent: AnActionEvent) {
        super.actionPerformed(actionEvent)
        FragmentDialog.create(this)
    }

    override fun onDialogOk(data: Fragment) {
        super.onDialogOk(data)
        FragmentGenerator().generateFiles(directory, data)
        FragmentLayoutGenerator().generateFiles(directory, data)
        ContractGenerator().generateFiles(directory, data.name)
        PresenterGenerator().generateFiles(directory, data.name)
    }

}