package com.nodes.plugin.generators.repository

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.generators.BaseGenerator
import com.nodes.plugin.generators.TemplateProperties
import com.nodes.plugin.models.Repository
import com.nodes.plugin.utils.TextUtils
import java.util.Properties

class RepositoryGenerator : BaseGenerator<Repository>() {

    override var getTemplate = TemplateMap.REPOSITORY

    override fun getClassName(modelObject: Repository) = "${modelObject.name}Repository"
    override fun getPackageName(modelObject: Repository) = modelObject.name

    override fun additionalProperties(modelObject: Repository, properties: Properties?): Properties? {
        return Properties().apply {
            setProperty(TemplateProperties.RETURN_TYPE, modelObject.returnType)
            setProperty(TemplateProperties.METHOD_NAME, TextUtils.textForMethodName(modelObject.returnType))
            setProperty(TemplateProperties.PARAM_NAME, TextUtils.textForParam(modelObject.returnType))
        }
    }
}