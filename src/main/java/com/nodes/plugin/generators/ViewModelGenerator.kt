package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.models.ViewModel
import java.util.*

class ViewModelGenerator : BaseGenerator<ViewModel>() {

    override val getTemplate = TemplateMap.VIEW_MODEL

    override fun getPackageName(modelObject: ViewModel) = modelObject.name
    override fun getClassName(modelObject: ViewModel) = "${modelObject.name}ViewModel"

    override fun additionalProperties(modelObject: ViewModel): Properties? {
        return Properties().apply {
            setProperty(TemplateProperties.VIEW_STATE_CLASS,    "${modelObject.name}ViewState")
        }
    }

}