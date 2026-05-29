package com.zosh.job.service;

import com.zosh.job.dto.JobRequest;
import com.zosh.job.dto.JobResponse;
import com.zosh.job.payload.JobSearchRequest;

import java.util.List;

public interface JobService {

    JobResponse createJob(Long employerId, JobRequest req);
    JobResponse getJobById(Long id);
    List<JobResponse> getJobs(JobSearchRequest request);
    List<JobResponse> getJobsByCompany(Long companyId);
    JobResponse updateJob(Long jobId,Long employerId, JobRequest req);
    JobResponse publishJob(Long jobId, Long employerId);
    JobResponse closeJob(Long jobId, Long employerId);
    JobResponse deleteJob(Long jobId, Long employerId);
    List<JobResponse> getAllJobsAdmin();
}
