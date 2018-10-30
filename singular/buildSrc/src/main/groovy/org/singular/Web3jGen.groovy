package org.singular

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import org.gradle.api.tasks.incremental.IncrementalTaskInputs
import org.web3j.codegen.ContractWrapperGenerator
import org.web3j.utils.Files

public class Web3jGen extends DefaultTask {
    public static final String DATA = ContractDependencies.DefaultDependencyFileName

    @InputDirectory
    def File abiDir       ///< where to hold the generated ABI and BIN fileNames

    @OutputDirectory
    def File wrapperBaseDir

    @Input
    def String wrapperPackageName;

    @Input
    @Optional
    def Boolean useNativeJavaTypes;

    @Input
    @Optional
    def List<String> excludedContracts;


    @TaskAction
    void execute(IncrementalTaskInputs inputs) {

        if (!inputs.incremental) {
            println('no incremental. do full build of thr wrappers')
            File[] wrappers = wrapperBaseDir.listFiles()
            if (wrappers)
                project.delete(wrappers)

            Set<String> files = allInputFiles()
            gen(files);
            return;
        } else {
            println('incremental...')
        }


        def Set<String> changedFiles = new HashSet<>()
        inputs.outOfDate { change ->
            def fname = change.file.canonicalPath
            if (fname.endsWith(".abi") || fname.endsWith(".bin")) {
                println("file changed: " + fname)
                fname = fname.substring(0, fname.length() - 4)
                changedFiles.add(fname)
            }
        }
        if (changedFiles.size() > 0) {
            gen(changedFiles)
        }

        inputs.removed { change ->
            def f = change.file.
            if (f.name.endsWith(".abi")) {
                println("file removed: " + f)
                def jname = f.name.replace("\\.abi", ".java")
                new File(wrapperBaseDir, jname).delete()
            }
        }

    }

    private HashSet<String> allInputFiles() {
        Set<String> files = new HashSet<>();

        abiDir.eachFileRecurse {
            def fname = it.canonicalPath;
            if (it.file && fname.endsWith(".abi") && !it.name.startsWith("_")) {
                fname = fname.replaceAll("\\.abi", "");
                if (excludedContracts == null || !excludedContracts.contains(contractName)) {
                    files.add(fname)
                }
            }
        }
        files
    }

    private void gen(Set<String> fileNames) {
        println('-------------------------------------------------')
        println("generating web3j wrappers in: " + wrapperBaseDir)
        println('-------------------------------------------------')

        fileNames.each {
            String contractName = it //it.substring(0, it.lastIndexOf("."));
            File contractBin = new File(contractName + ".bin");
            File contractAbi = new File(contractName + ".abi");
            def wrapper = new ContractWrapperGenerator(useNativeJavaTypes);
            contractName = contractName.substring(contractName.lastIndexOf(System.getProperty("file.separator")) + 1)
            if (!contractName.startsWith("_")) {
                if (excludedContracts == null || !excludedContracts.contains(contractName)) {

                    println("-- creating web3j wrapper for: " + contractName)
                    wrapper.generateJavaFiles(
                            contractName,
                            Files.readString(contractBin),
                            Files.readString(contractAbi),
                            wrapperBaseDir.path,
                            wrapperPackageName
                    );
                }
            }
        }
    }
}