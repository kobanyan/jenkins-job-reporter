package com.github.kobanyan.jenkins_job_reporter

import com.github.kobanyan.jenkins_job_reporter.handlers.DefaultJobHandler
import com.github.kobanyan.jenkins_job_reporter.handlers.Handler
import com.github.kobanyan.jenkins_job_reporter.handlers.PropertyHandler

import hudson.model.Job
import jenkins.model.Jenkins

class JenkinsJobReporter implements Serializable {

  List<Class<?>> includes
  List<Class<?>> excludes
  List<Handler> jobHandlers = [new DefaultJobHandler()]
  List<PropertyHandler<?>> propertyHandlers

  def report() {
    def result = []
    for (Job job : Jenkins.instance.getAllItems(Job.class)) {
      if (isInclude(job) && !isExclude(job)) {
        result.add(reportJob(job))
      }
    }
    return result
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
    for (Handler jobHandler : jobHandlers) {
      jobMap.putAll(jobHandler.report(job))
    }
    report['job'] = jobMap
    for (PropertyHandler propertyHandler : propertyHandlers) {
      def propertyClass = propertyHandler.getJobPropertyClass()
      def property = job.getProperty(propertyClass)
      def obj = propertyHandler.report(property)
      report[propertyClass.simpleName] = obj
    }
    return report
  }
}
