package com.github.kobanyan.jenkins_job_reporter.handlers

import jenkins.model.BuildDiscarderProperty

class BuildDiscarderPropertyHandler implements PropertyHandler<BuildDiscarderProperty> {

  @Override
  def report(BuildDiscarderProperty property) {
    if (property && property.strategy) {
      [
        'daysToKeep': Utils.nullToEmpty(property.strategy.daysToKeepStr),
        'numToKeep': Utils.nullToEmpty(property.strategy.numToKeepStr),
        'artifactDaysToKeep': Utils.nullToEmpty(property.strategy.artifactDaysToKeepStr),
        'artifactNumToKeep': Utils.nullToEmpty(property.strategy.artifactNumToKeepStr),
      ]
    } else {
      [
        'daysToKeep': '',
        'numToKeep': '',
        'artifactDaysToKeep': '',
        'artifactNumToKeep': '',
      ]
    }
  }

  @Override
  Class<BuildDiscarderProperty> getJobPropertyClass() {
    return BuildDiscarderProperty.class
  }

}
