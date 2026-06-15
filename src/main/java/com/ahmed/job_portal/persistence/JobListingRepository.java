package com.ahmed.job_portal.persistence;

import com.ahmed.job_portal.model.ExperienceLevel;
import com.ahmed.job_portal.model.JobListing;
import com.ahmed.job_portal.model.JobType;
import com.ahmed.job_portal.model.WorkMode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    public Optional<JobListing> findByCompanyId(Long id);

    public Optional<JobListing> findByTitleContainingIgnoreCase(String title);

    public Optional<JobListing> findByJobType(JobType jobType);

    public Optional<JobListing> findByWorkMode(WorkMode workMode);

    public Optional<JobListing> findByExperienceLevel(ExperienceLevel experienceLevel);
}