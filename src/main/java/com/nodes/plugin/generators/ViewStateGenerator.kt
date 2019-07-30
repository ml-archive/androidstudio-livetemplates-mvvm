package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.models.Naming
import com.nodes.plugin.models.ViewModel
import java.util.*

class ViewStateGenerator : BaseGenerator<Naming>() {

    override val getTemplate = TemplateMap.VIEW_STATE

    override fun getPackageName(modelObject: Naming) = modelObject.name
    override fun getClassName(modelObject: Naming) = "${modelObject.name.capitalize()}${modelObject.postFix ?: ""}ViewState"

    override fun additionalProperties(modelObject: Naming, properties: Properties?): Properties? {
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