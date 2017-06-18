package com.github.kobanyan.jenkins_job_reporter.handlers

import hudson.model.Job
import jenkins.model.ParameterizedJobMixIn.ParameterizedJob

class ParameterizedJobHandler implements JobHandler {

  @Override
  def report(Job<?, ?> job) {
    def key = 'parameterized'
    if (job instanceof ParameterizedJob) {
      ParameterizedJob<?, ?> paramJob = (ParameterizedJob<?, ?>) job
      return [
        "${key}": paramJob.parameterized
      ]
    } else {
      return [
        "${key}": ''
      ]
    }
  }

}
