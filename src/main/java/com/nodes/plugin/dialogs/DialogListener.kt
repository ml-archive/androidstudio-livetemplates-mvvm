package com.nodes.plugin.dialogs

interface DialogListener<T> {
    fun onDialogOk(data: T)
}
