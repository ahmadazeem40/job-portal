package com.ahmed.job_portal.persistence;

import com.ahmed.job_portal.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobApplicationsRepository extends JpaRepository<JobApplication, Long> {
    public List<JobApplication> findByUserId(Long id);

    public List<JobApplication> findByJobListingId(Long id);

    public Optional<JobApplication> findByUserIdAndJobListingId(Long userId, Long jobListingId);

    public boolean existsByUserIdAndJobListingId(Long userId, Long jobListingId);

}