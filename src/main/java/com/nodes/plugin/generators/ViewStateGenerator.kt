package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.models.ViewModel
import java.util.*

class ViewStateGenerator : BaseGenerator<String>() {

    override val getTemplate = TemplateMap.VIEW_STATE

    override fun getPackageName(modelObject: String) = modelObject
    override fun getClassName(modelObject: String) = "${modelObject.capitalize()}ViewState"

    override fun additionalProperties(modelObject: String, properties: Properties?): Properties? {
        val pack = properties?.get(TemplateProperties.PACKAGE_NAME) as String?
        return if(pack != null && pack.contains(".ui.")) {
            val subPack = pack.substring(0, pack.indexOf(".ui."))
            Properties().apply {
                setProperty(TemplateProperties.PACKAGE_PRESENTATION_NAME, subPack)
            }
        } else {
            Properties()
        }
    }

}