package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.models.Interactor
import java.util.Properties

class InteractorGenerator : BaseGenerator<Interactor>() {

    override var getTemplate = TemplateMap.INTERACTOR

    override fun getPackageName(modelObject: Interactor) = modelObject.name
    override fun getClassName(modelObject: Interactor) = "${modelObject.name}Interactor"

    override fun additionalProperties(modelObject: Interactor, properties: Properties?): Properties? {
        return Properties().apply {

            getTemplate = when {
                modelObject.inputType == null && modelObject.outputType == null -> TemplateMap.EMPTY_INTERACTOR
                modelObject.inputType == null -> TemplateMap.NO_INPUT_INTERACTOR
                modelObject.outputType == null -> TemplateMap.NO_OUTPUT_INTERACTOR
                else -> TemplateMap.INTERACTOR
            }
            setProperty(TemplateProperties.OUTPUT_TYPE, modelObject.outputType ?: "Unit")
            setProperty(TemplateProperties.INPUT_TYPE, modelObject.inputType ?: "Unit")

            val pack = properties?.get(TemplateProperties.PACKAGE_NAME) as String?
            if (pack != null && pack.contains(".domain.")) {
                val subPack = pack.substring(0, pack.indexOf(".domain."))
                setProperty(TemplateProperties.PACKAGE_DOMAIN_NAME, subPack)
            }
        }
    }
}
