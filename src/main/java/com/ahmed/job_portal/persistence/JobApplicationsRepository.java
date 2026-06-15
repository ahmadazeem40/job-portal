package com.ahmed.job_portal.persistence;

import com.ahmed.job_portal.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobApplicationsRepository extends JpaRepository<JobApplication, Long> {
    public Optional<JobApplication> findByUserId(Long id);

    public Optional<JobApplication> findByJobListingId(Long id);

    public Optional<JobApplication> existsByUserIdAndJobListingId(Long userId, Long jobListingId);

}