package org.singular;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.InputDirectory;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.incremental.IncrementalTaskInputs;

class IncrementalReverseTask extends DefaultTask {
      @InputDirectory
      def File inputDir

      @OutputDirectory
      def File outputDir

      @TaskAction
      void execute(IncrementalTaskInputs inputs) {
          println("run the inc reverse task")
          if (!inputs.incremental)
              project.delete(outputDir.listFiles())

          inputs.outOfDate { change ->
              def f = change.file
              println("file outOfDate: " + f)
              if (f.file) {
                  def targetFile = project.file("$outputDir/${f.name}")
                  targetFile.text = f.text.reverse()
              }
          }

          inputs.removed { change ->
              println("file removed: " + change.file)
              def targetFile = project.file("$outputDir/${change.file.name}")
              if (targetFile.exists()) {
                  targetFile.delete()
              }
          }
      }
  }