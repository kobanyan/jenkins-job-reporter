package com.github.kobanyan.jenkins_job_reporter.reporters

import hudson.model.Job

class DefaultJobReporter implements JobReporter {

  @Override
  def report(Job<?, ?> job) {
    [
      fullName: job.fullName,
      class: job.class.name,
      lastBuild: job.lastBuild
        ? new Date(job.lastBuild.startTimeInMillis).format('yyyy-MM-dd HH:mm:ss')
        : '',
    ]
  }
}
