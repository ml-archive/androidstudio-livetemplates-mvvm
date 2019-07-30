package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.models.Interactor
import java.util.*

class InteractorGenerator : BaseGenerator<Interactor>() {

    override val getTemplate = TemplateMap.INTERACTOR

    override fun getPackageName(modelObject: Interactor) = modelObject.name
    override fun getClassName(modelObject: Interactor) = "${modelObject.name}Interactor"

    override fun additionalProperties(modelObject: Interactor, properties: Properties?): Properties? {
        return Properties().apply {
            setProperty(TemplateProperties.RETURN_TYPE, modelObject.returnType)
        }
    }

}