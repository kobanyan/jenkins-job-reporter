package com.github.kobanyan.jenkins_job_reporter.reporters

import hudson.model.Job
import hudson.model.JobProperty

interface PropertyReporter<P extends JobProperty<Job<?,?>>> extends Reporter<P> {
  Class<P> getJobPropertyClass()
  String getName()
}
