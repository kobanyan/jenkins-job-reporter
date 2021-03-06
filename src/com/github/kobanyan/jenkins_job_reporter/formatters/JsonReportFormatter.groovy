package com.github.kobanyan.jenkins_job_reporter.formatters

import groovy.json.JsonOutput

class JsonReportFormatter implements ReportFormatter {

  @Override
  String format(report) {
    JsonOutput.toJson(report)
  }

}
