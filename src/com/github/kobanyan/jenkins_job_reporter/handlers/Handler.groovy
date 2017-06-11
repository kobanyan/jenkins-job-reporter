package com.github.kobanyan.jenkins_job_reporter.handlers

import java.util.Map

import hudson.model.Job

interface Handler<T> extends Serializable {
  Map<String, String> toMap(T target)
}
