package com.zosh.job.repository;

import com.zosh.job.modal.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findByCompanyId(Long companyId);
}
