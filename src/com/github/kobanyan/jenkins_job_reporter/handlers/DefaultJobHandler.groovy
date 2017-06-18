package com.github.kobanyan.jenkins_job_reporter.handlers

import hudson.model.Job

class DefaultJobHandler implements JobHandler {

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
