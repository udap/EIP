package org.singular

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import org.gradle.api.tasks.incremental.IncrementalTaskInputs
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class SolidityCompile extends DefaultTask {
    private static final Logger logger = LoggerFactory.getLogger(SolidityCompile)

    public static final String DATA = ContractDependencies.DefaultDependencyFileName

    @InputDirectory
    def File srcDir       ///< the contract source directory

//    @Input  //!! MUST NOT use this with the OutpurDirectory, or it will overwrite it.
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
            logger.debug('do full build')
            File[] abiFiles = abiDir.listFiles()
            if (abiFiles)
                project.delete(abiFiles)

            if (!srcDir.exists())
                throw new RuntimeException(srcDir.absolutePath + " does not exist")

            fullRebuild()
            return;
        } else {
            logger.debug('incremental...')
        }


        def Set<String> changedFiles = new HashSet<>()
        inputs.outOfDate { change ->
            logger.debug("file outOfDate: " + change.file)
            changedFiles.add(change.file.canonicalPath)
        }

        boolean removed = false;
        inputs.removed { change ->
            logger.debug("file removed: " + change.file)
            removed = true;
        }

        if (removed) {
            // do a full rebuild
            fullRebuild()
        } else {
            if (changedFiles.size() > 0) {
                changedFiles = ContractDependencies.restoreFrom(dependencyFileName).update(changedFiles);
                rebuild(changedFiles)
            }
        }
    }

    private void fullRebuild() {
        def allFiles = allInputFiles();
        ContractDependencies.fresh(dependencyFileName).update(allFiles);
        rebuild(allFiles)
    }

    private HashSet<String> allInputFiles() {
        Set<String> files = new HashSet<>();

        srcDir.eachFileRecurse {
            def fname = it.name;
            if (it.file && fname.endsWith(".sol")) {
                String contractName = fname.substring(0, fname.length() - 4);
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

        logger.debug(options.join(' '))

        project.exec {
            executable = 'solc'
            args = options
        }
    }
}