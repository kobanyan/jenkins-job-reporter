package com.github.kobanyan.jenkins_job_reporter.handlers

interface Handler<T> extends Serializable {
  def report(T target)
}
