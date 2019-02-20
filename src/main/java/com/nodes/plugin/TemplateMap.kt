package com.nodes.plugin

enum class TemplateMap(val templateName: String) {
    ACTIVITY("Activity"),
    BASIC_LAYOUT("basic_layout"),
    ANDROID_MANIFEST("AndroidManifest"),
    CONTRACT("Contract"),
    FRAGMENT("Fragment"),
    INTERACTOR("Interactor"),
    PRESENTER("Presenter"),
    REPOSITORY("Repository"),
    REPOSITORY_IMPL("RepositoryImpl"),
    VIEW_MODEL("ViewModel"),
    VIEW_STATE("ViewState")
}