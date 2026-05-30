package com.zosh.job.mapper;

import com.zosh.job.dto.CompanyResponse;
import com.zosh.job.dto.JobResponse;
import com.zosh.job.modal.Job;
import com.zosh.job.modal.embeddable.JobLocation;
import com.zosh.job.modal.embeddable.SalaryRange;

public class JobMapper {

    public static JobResponse toResponse(Job job, CompanyResponse companyResponse){

        JobLocation loc = job.getLocation();
        SalaryRange sal = job.getSalaryRange();

        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .responsibilities(job.getResponsibilities())
                .benefits(job.getBenefits())
                .company(companyResponse)
//                .category(toCategoryResponse(job.getCategory()))
//                .skills(skills)
//                .tags(tags)
                .address(loc != null ? loc.getAddress() : null)
                .city(loc != null ? loc.getCity() : null)
                .state(loc != null ? loc.getState() : null)
                .country(loc != null ? loc.getCountry() : null)
                .zipCode(loc != null ? loc.getZipCode() : null)
                //salary
                .minSalary(sal != null ? sal.getMinSalary() : null)
                .maxSalary(sal != null ? sal.getMaxSalary() : null)
//                .currency(sal != null ? sal.getCurrency() : null)
//                .salaryPeriod(sal != null ? sal.getPeriod() : null)
//                .salaryNegotiable(sal != null ? sal.getNegotiable() : null)
//                .salaryDisclosed(sal != null ? sal.getDisclosed() : null)
                // classification
                .jobType(job.getJobType())
                .workMode(job.getWorkMode())
                .experienceLevel(job.getExperienceLevel())
                .status(job.getStatus())
                // posting
                .openings(job.getOpenings())
                .applicationDeadline(job.getApplicationDeadline())
                .expiresAt(job.getExpiresAt())
                .active(job.getActive())
                // analytics
                //.viewCount(job.getViewCount())
                //.applicationCount(job.getApplicationCount())
                // timestamps
                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .publishedAt(job.getPublishedAt())
                .closedAt(job.getClosedAt())
                .build();
    };
}
