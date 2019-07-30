package com.nodes.plugin.generators

import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import com.nodes.plugin.TemplateMap
import com.nodes.plugin.utils.FileUtils
import java.util.*

abstract class BaseGenerator<T> {

    private var createFile: PsiFile? = null

    abstract val getTemplate: TemplateMap

    abstract fun getClassName(modelObject: T): String
    abstract fun getPackageName(modelObject: T): String

    open fun additionalProperties(modelObject: T, properties: Properties? = null): Properties? = null
    open fun getCustomDir(directory: PsiDirectory): PsiDirectory? = null
    open fun executeAdditionalActions(dir: PsiDirectory, generatedFile: PsiFile?, modelObject: T) {}

    private fun generateDefaultProperties(directory: PsiDirectory): Properties {
        return TemplateFileGenerator.getDefaultProperties(directory)
    }

    fun generateFiles(directory: PsiDirectory, modelObject: T) {
        var dir = FileUtils.makeDir(directory, getPackageName(modelObject)) ?: return
        if (getCustomDir(dir) != null) {
            dir = getCustomDir(dir)!!
        }

        val className = getClassName(modelObject)

        val properties = generateDefaultProperties(dir)
        properties.setProperty(TemplateProperties.CLASS_NAME, className)
        additionalProperties(modelObject, properties)?.forEach { properties[it.key] = it.value }

        createFile = TemplateFileGenerator.generateFile(className, dir, getTemplate, properties)
        executeAdditionalActions(dir, createFile, modelObject)
    }

}