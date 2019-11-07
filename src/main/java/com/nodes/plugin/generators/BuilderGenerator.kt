package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.models.Naming
import java.util.Properties

class BuilderGenerator : BaseGenerator<Naming>() {

    override var getTemplate = TemplateMap.Builder

    override fun getPackageName(modelObject: Naming) = modelObject.name
    override fun getClassName(modelObject: Naming) = "${modelObject.name.capitalize()}${if (modelObject.usePostFixInName) modelObject.postFix ?: "" else ""}Builder"

    override fun additionalProperties(modelObject: Naming, properties: Properties?): Properties? {
        val pack = properties?.get(TemplateProperties.PACKAGE_NAME) as String?
        return if (pack != null && pack.contains(".ui.")) {
            val subPack = pack.substring(0, pack.indexOf(".ui."))
            Properties().apply {
                setProperty(TemplateProperties.PACKAGE_PRESENTATION_NAME, subPack)
                setProperty(TemplateProperties.NAME, "${modelObject.name}${modelObject.postFix}")
                setProperty(TemplateProperties.CLASS, "${modelObject.name.capitalize()}${modelObject.postFix}")
                setProperty(TemplateProperties.VIEW_MODEL, "${modelObject.viewModelName}")
            }
        } else {
            Properties().apply {
                setProperty(TemplateProperties.NAME, "${modelObject.name}${modelObject.postFix}")
                setProperty(TemplateProperties.CLASS, "${modelObject.name.capitalize()}${modelObject.postFix}")
                setProperty(TemplateProperties.VIEW_MODEL, "${modelObject.viewModelName}")
            }
        }
    }
}
