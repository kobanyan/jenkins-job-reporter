package com.github.kobanyan.jenkins_job_reporter.styles

@Grab('org.yaml:snakeyaml:1.18')

import org.yaml.snakeyaml.Yaml;

class YamlReportFormatter implements ReportFormatter {

  @Override
  String format(report) {
    new Yaml().dump(report)
  }

}
