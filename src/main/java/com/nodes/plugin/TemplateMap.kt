package com.nodes.plugin

enum class TemplateMap(val templateName: String) {
    ACTIVITY("Activity"),
    BASIC_LAYOUT("basic_layout"),
    ANDROID_MANIFEST("AndroidManifest"),
    FRAGMENT("Fragment"),
    INTERACTOR("Interactor"),
    NO_INPUT_INTERACTOR("NoInputInteractor"),
    NO_OUTPUT_INTERACTOR("NoOutputInteractor"),
    EMPTY_INTERACTOR("EmptyInteractor"),
    REPOSITORY("Repository"),
    REPOSITORY_IMPL("RepositoryImpl"),
    VIEW_MODEL("ViewModel"),
    VIEW_STATE("ViewState"),
    Builder("Builder"),
    BuilderFragment("BuilderFragment"),
    BuilderActivity("BuilderActivity"),
    ENTITY("Entity"),
    LOCAL_DATA_SOURCE("LocalDataSource")
}
