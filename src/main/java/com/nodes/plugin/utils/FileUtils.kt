package com.nodes.plugin.utils

import com.intellij.openapi.application.ApplicationManager
import com.intellij.psi.PsiDirectory
import com.intellij.util.IncorrectOperationException
import java.util.concurrent.Semaphore

object FileUtils {

    fun makeDir(directory: PsiDirectory, dirName: String): PsiDirectory? {
        var dir: PsiDirectory? = directory.findSubdirectory(dirName.toLowerCase())

        if (dir?.isPhysical == true) {
            return dir
        }

        try {
            val s = Semaphore(0)
            ApplicationManager.getApplication().runWriteAction {
                dir = directory.createSubdirectory(dirName.toLowerCase())
                s.release()
            }
            s.acquire()
        } catch (e: IncorrectOperationException) {
            e.printStackTrace()
        }

        return dir
    }
}
