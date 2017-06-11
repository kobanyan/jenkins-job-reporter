package com.github.kobanyan.jenkins_job_reporter.handlers

import java.util.Map

import hudson.model.Job
import hudson.triggers.TimerTrigger

class DefaultJobHandler implements Handler<Job<?, ?>> {

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
