package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.models.Naming
import com.nodes.plugin.models.ViewModel
import java.util.*

class BuilderActivityGenerator : BaseGenerator<Naming>() {

    override val getTemplate = TemplateMap.BuilderActivity

    override fun getPackageName(modelObject: Naming) = modelObject.name + modelObject.postFix
    override fun getClassName(modelObject: Naming) = "${modelObject.name.capitalize()}${modelObject.postFix}Builder"

    override fun additionalProperties(modelObject: Naming, properties: Properties?): Properties? {
        val pack = properties?.get(TemplateProperties.PACKAGE_NAME) as String?
        return if(pack != null && pack.contains(".ui.")) {
            val subPack = pack.substring(0, pack.indexOf(".ui."))
            Properties().apply {
                setProperty(TemplateProperties.PACKAGE_PRESENTATION_NAME, subPack)
                setProperty(TemplateProperties.FRAGMENT_NAME,    "${modelObject}Fragment")
                setProperty(TemplateProperties.FRAGMENT_CLASS,    "${modelObject.name.capitalize()}${modelObject.postFix}Fragment")
                setProperty(TemplateProperties.VIEW_MODEL,    "${modelObject.name.capitalize()}${modelObject.postFix}ViewModel")
            }
        } else {
            Properties().apply {
                setProperty(TemplateProperties.FRAGMENT_NAME,    "${modelObject}Fragment")
                setProperty(TemplateProperties.FRAGMENT_CLASS,    "${modelObject.name.capitalize()}${modelObject.postFix}Fragment")
                setProperty(TemplateProperties.VIEW_MODEL,    "${modelObject.name.capitalize()}${modelObject.postFix}ViewModel")
            }
        }
    }

}