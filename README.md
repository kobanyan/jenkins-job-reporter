# Jenkins Job Reporter

A Jenkins Pipeline Library to report jenkins jobs.

## Usage

```groovy
@Library('jenkins-job-reporter')
import groovy.json.JsonOutput

import hudson.matrix.MatrixConfiguration

import com.github.kobanyan.jenkins_job_reporter.JenkinsJobReporter
import com.github.kobanyan.jenkins_job_reporter.handlers.BuildDiscarderPropertyHandler
import com.github.kobanyan.jenkins_job_reporter.handlers.DefaultJobHandler

excludes = [
  MatrixConfiguration.class,
]
propertyHandlers = [
  new BuildDiscarderPropertyHandler(),
]

node() {
  stage('Build Report') {
    def reporter = new JenkinsJobReporter()
    reporter.excludes = excludes
    reporter.propertyHandlers = propertyHandlers
    def report = reporter.report()
    def json = JsonOutput.toJson(report)
    stage('Archive Report') {
      def file = 'report.json'
      writeFile file: file, text: json
      archiveArtifacts artifacts: file
    }
  }
}
```