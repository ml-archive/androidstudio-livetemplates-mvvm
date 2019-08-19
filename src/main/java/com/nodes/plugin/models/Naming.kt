package com.nodes.plugin.models

data class Naming(
    val name: String,
    val postFix: String? = null,
    val usePostFixInName: Boolean = true,
    val viewModelName: String? = null
)