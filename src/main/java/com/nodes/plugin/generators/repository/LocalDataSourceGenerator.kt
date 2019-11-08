package com.nodes.plugin.generators.repository

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.generators.BaseGenerator
import com.nodes.plugin.generators.TemplateProperties
import com.nodes.plugin.models.Repository
import com.nodes.plugin.utils.TextUtils
import java.util.*

class LocalDataSourceGenerator : BaseGenerator<Repository>() {

    override var getTemplate = TemplateMap.LOCAL_DATA_SOURCE

    override fun getClassName(modelObject: Repository) =
        if (modelObject.roomRepository) "${modelObject.name}Dao" else "${modelObject.name}LocalDataSource"

    override fun getPackageName(modelObject: Repository) = modelObject.name

    override fun additionalProperties(modelObject: Repository, properties: Properties?): Properties? {
        return Properties().apply {
            setProperty(TemplateProperties.RETURN_TYPE, modelObject.returnType)
            setProperty(TemplateProperties.METHOD_NAME, TextUtils.textForMethodName(modelObject.returnType))
            setProperty(TemplateProperties.PARAM_NAME, TextUtils.textForParam(modelObject.returnType))
            setProperty(TemplateProperties.ROOM_ANNOTATIONS, modelObject.roomRepository.toString())
            setProperty(TemplateProperties.DOMAIN_ENTITY, modelObject.name + "Entity")
        }
    }

}