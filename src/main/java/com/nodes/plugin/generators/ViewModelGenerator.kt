package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.models.ViewModel
import java.util.*

class ViewModelGenerator : BaseGenerator<String>() {

    override val getTemplate = TemplateMap.VIEW_MODEL

    override fun getPackageName(modelObject: String) = modelObject
    override fun getClassName(modelObject: String) = "${modelObject.capitalize()}ViewModel"

    override fun additionalProperties(modelObject: String, properties: Properties?): Properties? {
        val pack = properties?.get(TemplateProperties.PACKAGE_NAME) as String?
        return if(pack != null && pack.contains(".ui.")) {
            val subPack = pack.substring(0, pack.indexOf(".ui."))
            Properties().apply {
                setProperty(TemplateProperties.VIEW_STATE_CLASS,    "${modelObject.capitalize()}ViewState")
                setProperty(TemplateProperties.PACKAGE_PRESENTATION_NAME, subPack)
            }
        } else {
            Properties().apply {
                setProperty(TemplateProperties.VIEW_STATE_CLASS,    "${modelObject.capitalize()}ViewState")
            }
        }
    }

}