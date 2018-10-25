package org.web3j.solidity.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.web3j.codegen.SolidityFunctionWrapper
import org.web3j.utils.Files

public class SolidityCompile extends DefaultTask {

    @Input
    public String srcDir       ///< the contract source directory

    @Input
    public String abiDir       ///< where to hold the generated ABI and BIN files

    @Input
    @Optional
    public Boolean overwrite

    @Input
    @Optional
    public Boolean optimize

    @Input
    @Optional
    public Integer optimizeRuns

    @Input
    @Optional
    public Boolean prettyJson

    @Input
    @Optional
    public OutputComponent[] outputArtifacts   ///< artifacts to generate

    @Input
    @Optional
    public String[] srcMaps

    /// the following configs are for wrapper generation
    
    @Input
    public String wrapperPackageName;

    @Input
    public String wrapperBaseDir

    @Input
    @Optional
    public Boolean useNativeJavaTypes;

    @Input
    @Optional
    public List<String> excludedContracts;


    @TaskAction
    void compileSolidity() {
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
//                options.add(options.add("--$OutputComponent.ASM_JSON"))
        }

        for (srcMap in srcMaps) {
            options.add(srcMap)
        }

        options.add('-o')
        options.add(abiDir)

        println(options)

//        for (File contract in source) {
//            options.add(contract.absolutePath)
//        }

        File sd = new File(srcDir)
        if (!sd.exists())
            throw new RuntimeException(sd.absolutePath + " does not exisit")

        new File(srcDir).eachFileRecurse {
            if (it.name.endsWith(".sol")) {
                String contractName = it.getName().replaceAll("\\.sol", "");
                if (excludedContracts == null || !excludedContracts.contains(contractName)) {
                    options.add(it.absolutePath)
                }
            }
        }

        project.exec {
            executable = 'solc'
            args = options
        }

        println("generating web3j wrapper")

        new File(abiDir).eachFile {
            if (it.name.endsWith(".abi") && !it.name.startsWith("_")) {
                String contractName = it.getName().replaceAll("\\.abi", "");
                if (excludedContracts == null || !excludedContracts.contains(contractName)) {
//                    String packageName = MessageFormat.format(
//                            wrapperPackageName,
//                            contractName.toLowerCase()
//                    );
                    File contractBin = new File(it.getParentFile(), contractName + ".bin");
                    def wrapper = new SolidityFunctionWrapper(useNativeJavaTypes);

                    println("creating web3j wrapper for: " + contractName)

                    wrapper.generateJavaFiles(
                            contractName,
                            Files.readString(contractBin),
                            Files.readString(it),
                            wrapperBaseDir,
                            wrapperPackageName
                    );
                }
            }
        }
    }
}
