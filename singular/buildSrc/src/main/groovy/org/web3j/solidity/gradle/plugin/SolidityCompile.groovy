package org.web3j.solidity.gradle.plugin

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.SourceTask
import org.gradle.api.tasks.TaskAction
import org.web3j.codegen.SolidityFunctionWrapper
import org.web3j.utils.Files


public class SolidityCompile extends SourceTask {

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
    public OutputComponent[] outputComponents

    @Input
    @Optional
    public String[] srcMaps

    /// the following configs are for wrapper generation
    
    @Input
    public String generatedJavaPackageName;

    @Input
    public String generatedFilesBaseDir

    @Input
    @Optional
    public Boolean useNativeJavaTypes;

    @Input
    @Optional
    public List<String> excludedContracts;


    @TaskAction
    void compileSolidity() {
        def options = []

        for (output in outputComponents) {
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

        def outdir = outputs.files.singleFile.absolutePath
        options.add('-o')
        options.add(outdir)

        println(options)

        for (File contract in source) {
            options.add(contract.absolutePath)
        }

        project.exec {
            executable = 'solc'
            args = options
        }

        println("generating web3j wrapper")

        new File(outdir).eachFile {
            if (it.name.endsWith(".abi") && !it.name.startsWith("_")) {
                String contractName = it.getName().replaceAll("\\.abi", "");
                if (excludedContracts == null || !excludedContracts.contains(contractName)) {
//                    String packageName = MessageFormat.format(
//                            generatedJavaPackageName,
//                            contractName.toLowerCase()
//                    );
                    File contractBin = new File(it.getParentFile(), contractName + ".bin");
                    def wrapper = new SolidityFunctionWrapper(useNativeJavaTypes);

                    println("creating web3j wrapper for: " + contractName)

                    wrapper.generateJavaFiles(
                            contractName,
                            Files.readString(contractBin),
                            Files.readString(it),
                            generatedFilesBaseDir,
                            generatedJavaPackageName
                    );
                }
            }
        }
    }
}
