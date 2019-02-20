package com.nodes.plugin.generators.fragment

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.generators.BaseGenerator
import com.nodes.plugin.generators.TemplateProperties
import com.nodes.plugin.models.Fragment
import java.util.*

class FragmentGenerator : BaseGenerator<Fragment>() {

    override val getTemplate: TemplateMap = TemplateMap.FRAGMENT

    override fun getPackageName(modelObject: Fragment) = modelObject.name
    override fun getClassName(modelObject: Fragment) = "${modelObject.name}Fragment"

    override fun additionalProperties(modelObject: Fragment): Properties? {
        return Properties().apply {
            setProperty(TemplateProperties.CONTRACT_NAME,   "${modelObject.name}Contract")
            setProperty(TemplateProperties.LAYOUT_NAME,      generateLayoutName(modelObject.name))
        }
    }

    private fun generateLayoutName(name: String) = "fragment_" + name.toLowerCase()

}