package com.github.kobanyan.jenkins_job_reporter.handlers

import hudson.model.Job

interface JobHandler extends Handler<Job<?, ?>> {

}
