package com.github.kobanyan.jenkins_job_reporter.styles

interface ReportFormatter extends Serializable {
  String format(report)
}
