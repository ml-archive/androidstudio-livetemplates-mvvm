package com.nodes.plugin.generators

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.nodes.plugin.TemplateMap

object TemplateFileGenerator {

    fun generateFile(fileName: String, directory: PsiDirectory, template: TemplateMap, customTemplateProperties: java.util.Properties): PsiFile? {

        val defaultProperties = getDefaultProperties(directory)
        customTemplateProperties.forEach { defaultProperties[it.key] = it.value }

        return try {
            createFileFromTemplate(
                fileName,
                template.templateName,
                directory,
                defaultProperties
            ) as PsiFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }

    fun getDefaultProperties(directory: PsiDirectory): java.util.Properties {
        val defaultProperties = FileTemplateManager.getInstance(directory.project).defaultProperties
        defaultProperties.setProperty(
            TemplateProperties.PACKAGE_NAME, getPackageName(
            directory
        ) ?: "")
        return defaultProperties
    }

    private fun createFileFromTemplate(name: String, templateName: String, directory: PsiDirectory, defaultProperties: java.util.Properties): PsiElement {
        val template = FileTemplateManager.getDefaultInstance().getInternalTemplate(templateName)
        return FileTemplateUtil.createFromTemplate(template, name, defaultProperties, directory)
    }

    private fun getPackageName(file: PsiDirectory): String? {
        return ProjectRootManager.getInstance(file.project)
                .fileIndex
                .getPackageNameByDirectory(file.virtualFile)
    }

}