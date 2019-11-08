package com.nodes.plugin.models

data class Entity(
    val name: String,
    val roomAnnotations: Boolean,
    val generateRepository: Boolean,
    val domainEntity: Boolean
)
