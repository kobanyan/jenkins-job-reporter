package com.github.kobanyan.jenkins_job_reporter.formatters

interface ReportFormatter extends Serializable {
  String format(report)
}
