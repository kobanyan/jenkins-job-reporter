package com.github.kobanyan.jenkins_job_reporter.handlers

import hudson.model.ParameterDefinition
import hudson.model.ParametersDefinitionProperty

class ParametersDefinitionPropertyHandler implements PropertyHandler<ParametersDefinitionProperty> {

  @Override
  def report(ParametersDefinitionProperty prop) {
    def jobParams = []
    if (prop && prop.parameterDefinitions) {
      for (ParameterDefinition param : prop.parameterDefinitions) {
        jobParams.add([
          type: param.type,
          name: param.name
        ])
      }
    }
    return jobParams
  }

  @Override
  Class<ParametersDefinitionProperty> getJobPropertyClass() {
    return ParametersDefinitionProperty.class;
  }

}
