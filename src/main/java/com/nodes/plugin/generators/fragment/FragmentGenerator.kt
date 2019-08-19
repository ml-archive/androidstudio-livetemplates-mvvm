package com.nodes.plugin.generators.fragment

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.generators.BaseGenerator
import com.nodes.plugin.generators.TemplateProperties
import com.nodes.plugin.models.Fragment
import java.util.*

class FragmentGenerator : BaseGenerator<Fragment>() {

    override val getTemplate: TemplateMap = TemplateMap.FRAGMENT

    override fun getPackageName(modelObject: Fragment) = modelObject.name
    override fun getClassName(modelObject: Fragment) = "${modelObject.name.capitalize()}Fragment"

    override fun additionalProperties(modelObject: Fragment, properties: Properties?): Properties? {
        val pack = properties?.get(TemplateProperties.PACKAGE_NAME) as String?
        return if(pack != null && pack.contains(".ui.")) {
            val subPack = pack.substring(0, pack.indexOf(".ui."))
            Properties().apply {
                setProperty(TemplateProperties.PACKAGE_PRESENTATION_NAME, subPack)
                setProperty(TemplateProperties.CONTRACT_NAME,   "${modelObject.name}Contract")
                setProperty(TemplateProperties.VIEW_MODEL,   "${modelObject.name.capitalize()}ViewModel")
                setProperty(TemplateProperties.LAYOUT_NAME,      generateLayoutName(modelObject.name))
            }
        } else {
            Properties().apply {
                setProperty(TemplateProperties.CONTRACT_NAME,   "${modelObject.name}Contract")
                setProperty(TemplateProperties.VIEW_MODEL,   "${modelObject.name.capitalize()}ViewModel")
                setProperty(TemplateProperties.LAYOUT_NAME,      generateLayoutName(modelObject.name))
            }
        }
    }

    private fun generateLayoutName(name: String) = "fragment_" + name.toLowerCase()

}