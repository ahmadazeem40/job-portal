package com.ahmed.job_portal.persistence;

import com.ahmed.job_portal.model.ExperienceLevel;
import com.ahmed.job_portal.model.JobListing;
import com.ahmed.job_portal.model.JobType;
import com.ahmed.job_portal.model.WorkMode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    public List<JobListing> findByCompanyId(Long id);

    public List<JobListing> findByTitleContainingIgnoreCase(String title);

    public List<JobListing> findByJobType(JobType jobType);

    public List<JobListing> findByWorkMode(WorkMode workMode);

    public List<JobListing> findByExperienceLevel(ExperienceLevel experienceLevel);
}