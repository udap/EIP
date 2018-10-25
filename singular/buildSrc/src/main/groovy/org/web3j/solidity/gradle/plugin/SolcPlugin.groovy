package org.web3j.solidity.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
/**
 * Gradle plugin for Solidity compile automation.
 */
class SolcPlugin implements Plugin<Project> {

    def PLUGIN = "solc"

    @Override
    void apply(final Project project) {
        def solcTask = project.getTasks().create(PLUGIN, SolidityCompile)
        solcTask.description = "Compiles Solidity contracts"
        project.getTasks().getByName('build').dependsOn(solcTask);
    }
}
