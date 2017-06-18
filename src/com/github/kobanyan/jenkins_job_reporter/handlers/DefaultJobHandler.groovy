package com.github.kobanyan.jenkins_job_reporter.handlers

import hudson.model.Job

class DefaultJobHandler implements JobHandler {

  @Override
  Map<String, String> toMap(Job<?, ?> job) {
    [
      fullName: job.fullName,
      class: job.class.name,
      lastBuild: job.lastBuild
        ? new Date(job.lastBuild.startTimeInMillis).format('yyyy-MM-dd HH:mm:ss')
        : '',
    ]
  }
}
