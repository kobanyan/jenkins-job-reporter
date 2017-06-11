package com.github.kobanyan.jenkins_job_reporter.reporters

import hudson.model.Job
import jenkins.model.ParameterizedJobMixIn.ParameterizedJob

class ParameterizedJobReporter implements JobReporter {

  @Override
  def report(Job<?, ?> job) {
    if (job instanceof ParameterizedJob) {
      ParameterizedJob<?, ?> paramJob = (ParameterizedJob<?, ?>) job
      return [
        parameterized: paramJob.parameterized
      ]
    } else {
      return [
        parameterized: ''
      ]
    }
  }

}
