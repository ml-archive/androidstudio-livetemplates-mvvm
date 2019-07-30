package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.models.ViewModel
import java.util.*

class BuilderGenerator : BaseGenerator<String>() {

    override val getTemplate = TemplateMap.Builder

    override fun getPackageName(modelObject: String) = modelObject
    override fun getClassName(modelObject: String) = "${modelObject.capitalize()}Builder"

    override fun additionalProperties(modelObject: String, properties: Properties?): Properties? {
        val pack = properties?.get(TemplateProperties.PACKAGE_NAME) as String?
        return if(pack != null && pack.contains(".ui.")) {
            val subPack = pack.substring(0, pack.indexOf(".ui."))
            Properties().apply {
                setProperty(TemplateProperties.PACKAGE_PRESENTATION_NAME, subPack)
                setProperty(TemplateProperties.FRAGMENT_NAME,    "${modelObject}Fragment")
                setProperty(TemplateProperties.FRAGMENT_CLASS,    "${modelObject.capitalize()}Fragment")
                setProperty(TemplateProperties.VIEW_MODEL,    "${modelObject.capitalize()}ViewModel")
            }
        } else {
            Properties().apply {
                setProperty(TemplateProperties.FRAGMENT_NAME,    "${modelObject}Fragment")
                setProperty(TemplateProperties.FRAGMENT_CLASS,    "${modelObject.capitalize()}Fragment")
                setProperty(TemplateProperties.VIEW_MODEL,    "${modelObject.capitalize()}ViewModel")
            }
        }
    }

}