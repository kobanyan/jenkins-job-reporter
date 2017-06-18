package com.github.kobanyan.jenkins_job_reporter.reporters

interface Reporter<T> extends Serializable {
  def report(T target)
}
