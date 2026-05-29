package com.zosh.job.service;

import com.zosh.job.dto.JobRequest;
import com.zosh.job.dto.JobResponse;

public interface JobService {
    JobResponse createJob(Long employerId, JobRequest req);
}
