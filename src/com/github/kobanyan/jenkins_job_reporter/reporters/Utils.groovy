package com.github.kobanyan.jenkins_job_reporter.reporters

class Utils {
  protected static final String nullToEmpty(value) {
    value == null ? '' : value
  }
}
