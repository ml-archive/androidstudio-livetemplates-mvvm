package com.nodes.plugin.generators.activity

import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import com.intellij.psi.util.ClassUtil
import com.intellij.psi.xml.XmlFile
import com.nodes.plugin.TemplateMap
import com.nodes.plugin.generators.BaseGenerator
import com.nodes.plugin.generators.TemplateFileGenerator
import com.nodes.plugin.generators.TemplateProperties
import com.nodes.plugin.models.Activity
import java.util.*

class ActivityGenerator : BaseGenerator<Activity>() {

    override val getTemplate: TemplateMap = TemplateMap.ACTIVITY

    override fun getPackageName(modelObject: Activity) = modelObject.name
    override fun getClassName(modelObject: Activity) = "${modelObject.name.capitalize()}Activity"

    override fun additionalProperties(modelObject: Activity, properties: Properties?): Properties? {
        return Properties().apply {
            setProperty(TemplateProperties.CONTRACT_NAME,   "${modelObject.name}Contract")
            setProperty(TemplateProperties.LAYOUT_NAME,     generateActivityLayoutName(modelObject.name))
        }
    }

    override fun executeAdditionalActions(dir: PsiDirectory, generatedFile: PsiFile?, modelObject: Activity) {
        super.executeAdditionalActions(dir, generatedFile, modelObject)
        generatedFile ?: return
        registerActivity(
            getManifest(dir),
            "${getCreatedPackage(dir)}.${generatedFile.virtualFile.nameWithoutExtension}"
        )
    }

    private fun getManifest(directory: PsiDirectory): PsiFile? {
        return ClassUtil.sourceRoot(directory).parent?.findFile("AndroidManifest.xml")
    }

    private fun getCreatedPackage(dir: PsiDirectory): String {
        return TemplateFileGenerator.getDefaultProperties(dir).getProperty("packageName")
    }

    private fun registerActivity(manifest: PsiFile?, activityPath: String) {
        val tag = manifest as XmlFile? ?: return
        val applicationTag = tag.rootTag?.findFirstSubTag("application")
        val projectPath = tag.rootTag?.getAttribute("package")?.value ?: ""
        val path = activityPath.replace(projectPath, "")
        val r = Runnable {
            CommandProcessor.getInstance()
                .executeCommand(
                    manifest?.project,
                    {
                        val xmlTag = applicationTag?.createChildTag("activity", applicationTag.namespace, "", true)
                        xmlTag?.setAttribute("android:name", path)
                        applicationTag?.addSubTag(xmlTag, false)
                    },
                    "createActivity",
                    "activity"
                )
        }
        WriteCommandAction.runWriteCommandAction(manifest?.project, r)
    }

    private fun generateActivityLayoutName(name: String) = "activity_" + name.toLowerCase()


}