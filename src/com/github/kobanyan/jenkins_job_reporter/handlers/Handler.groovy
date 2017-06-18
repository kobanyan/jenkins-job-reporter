package com.github.kobanyan.jenkins_job_reporter.handlers

interface Handler<T> extends Serializable {
  Map<String, String> toMap(T target)
}
