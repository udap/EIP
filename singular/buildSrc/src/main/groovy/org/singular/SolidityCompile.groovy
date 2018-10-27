package org.singular

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.incremental.IncrementalTaskInputs
import org.web3j.codegen.SolidityFunctionWrapper
import org.web3j.utils.Files

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

    /// the following configs are for wrapper generation

    @Input
    def String wrapperPackageName;

    @OutputDirectory
    def File wrapperBaseDir

    @Input
    @Optional
    def Boolean useNativeJavaTypes;

    @Input
    @Optional
    def String dependencyFileName = DATA;

    @Input
    @Optional
    def List<String> excludedContracts;


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

            File[] wrappers = wrapperBaseDir.listFiles()
            if (wrappers)
                project.delete(wrappers)

            if (!srcDir.exists())
                throw new RuntimeException(srcDir.absolutePath + " does not exisit")

            Set<String> files = allInputFiles()
            ContractDependencies.restoreFrom(dependencyFileName).update(files);
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

//        for (File contract in source) {
//            options.add(contract.absolutePath)
//        }


        project.exec {
            executable = 'solc'
            args = options
        }

        println('-------------------------------------------------')
        println("generating web3j wrappers in: " + wrapperBaseDir)
        println('-------------------------------------------------')

        abiDir.eachFile {
            if (it.name.endsWith(".abi") && !it.name.startsWith("_")) {
                String contractName = it.getName().replaceAll("\\.abi", "");
                if (excludedContracts == null || !excludedContracts.contains(contractName)) {
                    File contractBin = new File(it.getParentFile(), contractName + ".bin");
                    def wrapper = new SolidityFunctionWrapper(useNativeJavaTypes);

                    println("-- creating web3j wrapper for: " + contractName)

                    wrapper.generateJavaFiles(
                            contractName,
                            Files.readString(contractBin),
                            Files.readString(it),
                            wrapperBaseDir.path,
                            wrapperPackageName
                    );
                }
            }
        }
    }
}