package com.zosh.job.service.impl;

import com.zosh.job.dto.CompanyResponse;
import com.zosh.job.dto.JobRequest;
import com.zosh.job.dto.JobResponse;
import com.zosh.job.mapper.JobMapper;
import com.zosh.job.modal.Job;
import com.zosh.job.modal.embeddable.JobLocation;
import com.zosh.job.modal.embeddable.SalaryRange;
import com.zosh.job.payload.JobSearchRequest;
import com.zosh.job.repository.JobRepository;
import com.zosh.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImp implements JobService {

    private final JobRepository jobRepository;

    @Override
    public JobResponse createJob(Long employerId, JobRequest req) {
        //todo: fetch company by employer id
        Long companyId = 1L;

        Job job = Job.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .requirements(req.getRequirements())
                .responsibilities(req.getResponsibilities())
                .benefits(req.getBenefits())
                .companyId(companyId)
//                .category(category)
//                .skills(skills)
//                .tags(tags)
                .location(buildLocation(req))
                .salaryRange(buildSalaryRange(req))
                .jobType(req.getJobType())
                .workMode(req.getWorkMode())
                .experienceLevel(req.getExperienceLevel())
                .openings(req.getOpenings() != null ? req.getOpenings() : 1)
                .applicationDeadline(req.getApplicationDeadline())
                .expiresAt(req.getExpiresAt())
                .build();

        Job savedJob = jobRepository.save(job);
        return convertToResponse(savedJob);
    }

    @Override
    public JobResponse getJobById(Long id) throws Exception {
        Job job = jobRepository.findById(id).orElseThrow(
                ()-> new Exception("Job not found")
        );
        return convertToResponse(job);
    }

    @Override
    public List<JobResponse> getJobs(JobSearchRequest request) {
        return List.of();
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) {
        return List.of();
    }

    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest req) {
        return null;
    }

    @Override
    public JobResponse publishJob(Long jobId, Long employerId) {
        return null;
    }

    @Override
    public JobResponse closeJob(Long jobId, Long employerId) {
        return null;
    }

    @Override
    public JobResponse deleteJob(Long jobId, Long employerId) {
        return null;
    }

    @Override
    public List<JobResponse> getAllJobsAdmin() {
        return List.of();
    }

    private JobResponse convertToResponse(Job savedJob) {
        //todo : fetch company response
        CompanyResponse companyResponse = CompanyResponse.builder()
                .id(savedJob.getCompanyId())
                .build();

        return JobMapper.toResponse(savedJob,companyResponse);
    }

    private SalaryRange buildSalaryRange(JobRequest req) {
        return SalaryRange.builder()
                .minSalary(req.getMinSalary())
                .maxSalary(req.getMaxSalary())
                .build();
    }

    private JobLocation buildLocation(JobRequest req) {
        return JobLocation.builder()
                .address(req.getAddress())
                .city(req.getCity())
                .state(req.getState())
                .country(req.getCountry())
                .zipCode(req.getZipCode())
                .build();
    }
}
