package org.singular

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import org.gradle.api.tasks.incremental.IncrementalTaskInputs

public class SolidityCompile extends DefaultTask {


    public static final String DATA = "src/tmp/dependencies.data"

    SolidityCompile() {
//        println("new instane of SoidityCompile created")
    }

    @InputDirectory
    def File srcDir       ///< the contract source directory

    // should this be both InputDirectory and OutputDirectory?
    @Input
    @OutputDirectory
    def File abiDir       ///< where to hold the generated ABI and BIN files

    @Input
    @Optional
    def Boolean overwrite

    @Input
    @Optional
    def Boolean optimize

    @Input
    @Optional
    def Integer optimizeRuns

    @Input
    @Optional
    def Boolean prettyJson

    @Input
    @Optional
    def OutputArtifact[] outputArtifacts   ///< artifacts to generate

    @Input
    @Optional
    def String[] otherOptions

    @Input
    @Optional
    def String dependencyFileName = DATA;


    @Input
    @Optional
    def String[] excludedContracts


    void compileSolidity() {
        rebuild()
    }

    @TaskAction
    void execute(IncrementalTaskInputs inputs) {

        if (!inputs.incremental) {
            println('do full build')
            File[] abiFiles = abiDir.listFiles()
            if (abiFiles)
                project.delete(abiFiles)

            if (!srcDir.exists())
                throw new RuntimeException(srcDir.absolutePath + " does not exisit")

            Set<String> files = allInputFiles()
            ContractDependencies.fresh(dependencyFileName).update(files);
            rebuild(files);
            return;
        } else {
            println('incremental...')
        }


        def Set<String> changedFiles = new HashSet<>()
        inputs.outOfDate { change ->
            println("file outOfDate: " + change.file)
            changedFiles.add(change.file.canonicalPath)
        }

        boolean removed = false;
        inputs.removed { change ->
            println("file removed: " + change.file)
            removed = true;
        }

        if (removed) {
            // do a full rebuild
            def allFiles = allInputFiles();
            ContractDependencies.restoreFrom(dependencyFileName).update(allFiles);
            rebuild(allFiles)
        } else {
            if (changedFiles.size() > 0) {
                changedFiles = ContractDependencies.restoreFrom(dependencyFileName).update(changedFiles);
                rebuild(changedFiles)
            }
        }
    }

    private HashSet<String> allInputFiles() {
        Set<String> files = new HashSet<>();

        srcDir.eachFileRecurse {
            def fname = it.name;
            if (it.file && fname.endsWith(".sol")) {
                String contractName = it.getName().replaceAll("\\.sol", "");
                if (excludedContracts == null || !excludedContracts.contains(contractName)) {
                    files.add(it.canonicalPath)
                }
            }
        }
        files
    }

    private void rebuild(Set<String> files) {
        def options = []

        for (output in outputArtifacts) {
            options.add("--$output")
        }

        if (optimize) {
            options.add('--optimize')

            if (0 < optimizeRuns) {
                options.add('--optimize-runs')
                options.add(optimizeRuns)
            }
        }

        if (overwrite) {
            options.add('--overwrite')
        }

        if (prettyJson) {
            options.add('--pretty-json')
//                options.add(options.add("--$OutputArtifact.ASM_JSON"))
        }

        for (srcMap in otherOptions) {
            options.add(srcMap)
        }

        options.add('-o')
        options.add(abiDir)

        options.addAll(files);

        println(options)

        project.exec {
            executable = 'solc'
            args = options
        }
    }
}