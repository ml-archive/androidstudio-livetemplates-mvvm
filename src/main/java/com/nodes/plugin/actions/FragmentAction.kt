package com.nodes.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.nodes.plugin.dialogs.FragmentDialog
import com.nodes.plugin.generators.BuilderFragmentGenerator
import com.nodes.plugin.generators.ViewModelGenerator
import com.nodes.plugin.generators.ViewStateGenerator
import com.nodes.plugin.generators.fragment.FragmentGenerator
import com.nodes.plugin.generators.fragment.FragmentLayoutGenerator
import com.nodes.plugin.models.Fragment
import com.nodes.plugin.models.Naming

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
        ViewModelGenerator().generateFiles(directory, Naming(data.name))
        ViewStateGenerator().generateFiles(directory, Naming(data.name))
        BuilderFragmentGenerator().generateFiles(directory, data.name)
    }

}