package com.ahmed.job_portal.service;

import com.ahmed.job_portal.model.JobApplication;
import com.ahmed.job_portal.model.Status;
import com.ahmed.job_portal.persistence.JobApplicationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {
    private final JobApplicationsRepository jobApplicationsRepository;
    private final UserService userService;
    private final JobListingService jobListingService;

    public JobApplicationService(JobApplicationsRepository jobApplicationsRepository, UserService userService, JobListingService jobListingService) {
        this.jobApplicationsRepository = jobApplicationsRepository;
        this.userService = userService;
        this.jobListingService = jobListingService;
    }

    public JobApplication applyToJob(Long userId, Long listingId) {
        if (jobApplicationsRepository.existsByUserIdAndJobListingId(userId, listingId)){
            throw new RuntimeException("Application found with User id: " + userId + " and listing id: " + listingId);
        }
        if (jobListingService.getJobListingById(listingId).getStatus().equals(Status.CLOSED)){
            throw new RuntimeException("Listing with id " + listingId + " is already closed");
        }
        JobApplication jobApplication = new JobApplication();
        jobApplication.setUser(userService.getUserById(userId));
        jobApplication.setJobListing(jobListingService.getJobListingById(listingId));
        return jobApplicationsRepository.save(jobApplication);
    }

    public void withdrawApplication(Long id) {
        if (jobApplicationsRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Application not found with id: " + id);
        }
        jobApplicationsRepository.deleteById(id);
    }

    public JobApplication getJobApplicationById(Long id) {
        if (!jobApplicationsRepository.existsById(id)) {
            throw new RuntimeException("Application not found with id: " + id);
        }
        return jobApplicationsRepository.findById(id).get();
    }

    public List<JobApplication> getJobApplicationsByUserId(Long userId) {
        if (!jobApplicationsRepository.findByUserId(userId).isEmpty()) {
            throw new RuntimeException("Application not found with User id: " + userId);
        }
        return jobApplicationsRepository.findByUserId(userId);
    }

    public List<JobApplication> getJobApplicationsByListingId(Long listingId) {
        if (!jobApplicationsRepository.findByJobListingId(listingId).isEmpty()) {
            throw new RuntimeException("Application not found with Listing id: " + listingId);
        }
        return jobApplicationsRepository.findByJobListingId(listingId);
    }

    public boolean hasUserAlreadyApplied(Long userId, Long listingId) {
        return jobApplicationsRepository.existsByUserIdAndJobListingId(userId, listingId);
    }
}
