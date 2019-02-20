package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap

class ContractGenerator : BaseGenerator<String>() {

    override val getTemplate: TemplateMap = TemplateMap.CONTRACT

    override fun getPackageName(modelObject: String) = modelObject
    override fun getClassName(modelObject: String) = "${modelObject}Contract"

}