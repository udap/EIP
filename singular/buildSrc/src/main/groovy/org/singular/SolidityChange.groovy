package org.singular

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import org.gradle.api.tasks.incremental.IncrementalTaskInputs

public class SolidityChange extends DefaultTask {
    public static final String DATA = ContractDependencies.DefaultDependencyFileName

    @InputDirectory
    File srcDir       ///< the contract source directory

    // should this be both InputDirectory and OutputDirectory?
    @OutputDirectory
    File abiDir       ///< where to hold the generated ABI and BIN files

    @TaskAction
    void execute(IncrementalTaskInputs inputs) {
        println("run the inc change task")
        if (!inputs.incremental) {
            println("no incre")
            project.delete(abiDir.listFiles())
        }

        inputs.outOfDate { change ->
            def f = change.file
            println("file outOfDate: " + f)
            if (f.file) {
                def targetFile = project.file("$abiDir/${f.name}")
                targetFile.text = f.text.reverse()
            }
        }

        inputs.removed { change ->
            println("file removed: " + change.file)
            def targetFile = project.file("$abiDir/${change.file.name}")
            if (targetFile.exists()) {
                targetFile.delete()
            }
        }
    }
}