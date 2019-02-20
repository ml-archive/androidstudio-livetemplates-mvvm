package com.nodes.plugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import com.nodes.plugin.dialogs.DialogListener
import javax.annotation.OverridingMethodsMustInvokeSuper

abstract class BaseAction<T> : AnAction(), DialogListener<T> {

    abstract var modelObject: T

    lateinit var actionEvent: AnActionEvent
    lateinit var directory: PsiDirectory

    @OverridingMethodsMustInvokeSuper
    override fun actionPerformed(actionEvent: AnActionEvent) {
        this.actionEvent = actionEvent
    }

    @OverridingMethodsMustInvokeSuper
    override fun onDialogOk(data: T) {
        this.modelObject = data
        this.directory = actionEvent.getData(CommonDataKeys.PSI_ELEMENT) as PsiDirectory
    }

}