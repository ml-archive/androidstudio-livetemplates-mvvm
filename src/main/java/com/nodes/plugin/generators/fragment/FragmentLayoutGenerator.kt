package com.nodes.plugin.generators.fragment

import com.intellij.psi.PsiDirectory
import com.intellij.psi.util.ClassUtil
import com.nodes.plugin.TemplateMap
import com.nodes.plugin.generators.BaseGenerator
import com.nodes.plugin.models.Fragment

class FragmentLayoutGenerator : BaseGenerator<Fragment>() {

    override val getTemplate: TemplateMap = TemplateMap.BASIC_LAYOUT

    override fun getPackageName(modelObject: Fragment) = modelObject.name
    override fun getClassName(modelObject: Fragment) = generateLayoutName(modelObject.name)

    override fun getCustomDir(directory: PsiDirectory): PsiDirectory? {
        return ClassUtil.sourceRoot(directory).parent?.findSubdirectory("res")?.findSubdirectory("layout")
    }

    private fun generateLayoutName(name: String) = "fragment_" + name.toLowerCase()

}