package com.zosh.job.service;

public interface JobService {
    JobResponse createJob(Long employerId, JobRequest req);
}
