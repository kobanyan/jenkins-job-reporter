# Jenkins Job Reporter

A Jenkins Pipeline Shared Library to report jenkins jobs.

## Usage

```groovy
@Library('jenkins-job-reporter')

import groovy.json.JsonOutput

excludes = [
  hudson.matrix.MatrixConfiguration.class,
]
jobHandlers = [
  new com.github.kobanyan.jenkins_job_reporter.reporters.DefaultJobReporter(),
  new com.github.kobanyan.jenkins_job_reporter.reporters.ParameterizedJobReporter(),
]
propertyHandlers = [
  new com.github.kobanyan.jenkins_job_reporter.reporters.BuildDiscarderPropertyReporter(),
  new com.github.kobanyan.jenkins_job_reporter.reporters.ParametersDefinitionPropertyReporter(),
]

node() {
  stage('Build Report') {
    def reporter = new com.github.kobanyan.jenkins_job_reporter.JenkinsJobReporter(
      excludes: excludes,
      jobHandlers: jobHandlers,
      propertyHandlers: propertyHandlers,
    )
    def report = reporter.report()
    def json = groovy.json.JsonOutput.toJson(report)
    stage('Archive Report') {
      def file = 'report.json'
      writeFile file: file, text: json
      archiveArtifacts artifacts: file
    }
  }
}
```