package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import java.util.*

class PresenterGenerator : BaseGenerator<String>() {

    override val getTemplate: TemplateMap = TemplateMap.PRESENTER

    override fun getPackageName(modelObject: String) = modelObject
    override fun getClassName(modelObject: String) = "${modelObject}Presenter"

    override fun additionalProperties(modelObject: String): Properties? {
        return Properties().apply {
            setProperty(TemplateProperties.CONTRACT_NAME,   "${modelObject}Contract")
        }
    }

}