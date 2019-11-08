package com.nodes.plugin.generators.entity

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.generators.BaseGenerator
import com.nodes.plugin.generators.TemplateProperties
import com.nodes.plugin.models.Entity
import java.util.*

class EntityGenerator : BaseGenerator<Entity>() {
    override var getTemplate: TemplateMap = TemplateMap.ENTITY

    override fun getClassName(modelObject: Entity): String = modelObject.name + "Entity"
    override fun getPackageName(modelObject: Entity): String = modelObject.name

    override fun additionalProperties(modelObject: Entity, properties: Properties?): Properties? {
        return Properties().apply {
            setProperty(TemplateProperties.ROOM_ANNOTATIONS, modelObject.roomAnnotations.toString())
            setProperty(TemplateProperties.DOMAIN_ENTITY, modelObject.domainEntity.toString())
        }
    }
}