package com.nodes.plugin.listeners

interface FinishListener<T> {
    fun onFinished(result: T)
    fun onFailed(msg: String)
}