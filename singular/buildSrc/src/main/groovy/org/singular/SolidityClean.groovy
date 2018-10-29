package org.singular

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

public class SolidityClean extends DefaultTask {

    // should this be both InputDirectory and OutputDirectory?
    @Input
    def File abiDir       ///< where to hold the generated ABI and BIN files

    @Input
    def File wrapperBaseDir

//    def String dependencyFileName = "src/tmp/dependencies.data";

    @TaskAction
    void run() {
        println('delete solidity artifacts...')
        File[] abiFiles = abiDir.listFiles()
        if (abiFiles)
            project.delete(abiFiles)

        File[] wrappers = wrapperBaseDir.listFiles()
        if (wrappers)
            project.delete(wrappers)

        project.delete(ContractDependencies.DefaultDependencyFileName)
    }
}