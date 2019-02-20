package com.nodes.plugin.generators

import com.nodes.plugin.TemplateMap
import com.nodes.plugin.models.ViewModel

class ViewStateGenerator : BaseGenerator<ViewModel>() {

    override val getTemplate = TemplateMap.VIEW_STATE

    override fun getPackageName(modelObject: ViewModel) = modelObject.name
    override fun getClassName(modelObject: ViewModel) = "${modelObject.name}ViewState"

}