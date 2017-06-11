package com.github.kobanyan.jenkins_job_reporter.handlers

import hudson.model.Job
import hudson.model.JobProperty

interface PropertyHandler<P extends JobProperty<Job<?,?>>> extends Handler<P> {
  Class<P> getJobPropertyClass()
}
