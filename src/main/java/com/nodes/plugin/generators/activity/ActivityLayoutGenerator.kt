package com.nodes.plugin.generators.activity

import com.intellij.psi.PsiDirectory
import com.intellij.psi.util.ClassUtil
import com.nodes.plugin.TemplateMap
import com.nodes.plugin.generators.BaseGenerator
import com.nodes.plugin.models.Activity

class ActivityLayoutGenerator : BaseGenerator<Activity>() {

    override val getTemplate: TemplateMap = TemplateMap.BASIC_LAYOUT

    override fun getPackageName(modelObject: Activity) = modelObject.name
    override fun getClassName(modelObject: Activity) = generateActivityLayoutName(modelObject.name)

    override fun getCustomDir(directory: PsiDirectory): PsiDirectory? {
        return ClassUtil.sourceRoot(directory).parent?.findSubdirectory("res")?.findSubdirectory("layout")
    }

    private fun generateActivityLayoutName(name: String) = "activity_" + name.toLowerCase()
}
