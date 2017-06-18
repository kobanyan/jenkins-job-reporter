package com.github.kobanyan.jenkins_job_reporter

import com.github.kobanyan.jenkins_job_reporter.reporters.DefaultJobReporter
import com.github.kobanyan.jenkins_job_reporter.reporters.PropertyReporter
import com.github.kobanyan.jenkins_job_reporter.reporters.Reporter
import com.github.kobanyan.jenkins_job_reporter.formatters.JsonReportFormatter
import com.github.kobanyan.jenkins_job_reporter.formatters.ReportFormatter

import hudson.model.Job
import jenkins.model.Jenkins

class JenkinsJobReporter implements Serializable {

  List<Class<?>> includes
  List<Class<?>> excludes
  List<Reporter> jobHandlers = [new DefaultJobReporter()]
  List<PropertyReporter<?>> propertyHandlers
  ReportFormatter formatter = new JsonReportFormatter()

  def report() {
    def report = []
    for (Job job : Jenkins.instance.getAllItems(Job.class)) {
      if (isInclude(job) && !isExclude(job)) {
        report.add(reportJob(job))
      }
    }
    return formatter.format(report)
  }

  private def isInclude(Job job) {
    if (!includes) {
      return true
    }
    for (Class<?> include : includes) {
      if (include.isAssignableFrom(job.class)) {
        return true
      }
    }
    return false
  }

  private def isExclude(Job job) {
    if (!excludes) {
      return false
    }
    for (Class<?> exclude : excludes) {
      if (job.class == exclude) {
        return true
      }
    }
    return false
  }

  private def reportJob(Job job) {
    def report = [:]
    def jobMap = [:]
    for (Reporter jobHandler : jobHandlers) {
      jobMap.putAll(jobHandler.report(job))
    }
    report['job'] = jobMap
    for (PropertyReporter propertyHandler : propertyHandlers) {
      def propertyClass = propertyHandler.getJobPropertyClass()
      def property = job.getProperty(propertyClass)
      def obj = propertyHandler.report(property)
      report[propertyClass.simpleName] = obj
    }
    return report
  }
}
