package com.nodes.plugin.utils

object TextUtils {

    @JvmStatic
    fun textForParam(string: String): String {
        return string.replace("<", "")
                .replace(">", "")
                .replace("List", "")
                .decapitalize()
    }

    @JvmStatic
    fun textForMethodName(string: String): String {
        return string.replace("<", "")
                .replace(">", "")
    }

    @JvmStatic
    fun isValidInputChar(char: Char): Boolean {
        return !char.isWhitespace()
    }

    @JvmStatic
    fun String.caps() = this.capitalize()
}
