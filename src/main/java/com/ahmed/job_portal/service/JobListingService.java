package com.ahmed.job_portal.service;

import com.ahmed.job_portal.api.dto.JobListingDto;
import com.ahmed.job_portal.model.*;
import com.ahmed.job_portal.persistence.CompanyRepository;
import com.ahmed.job_portal.persistence.JobListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobListingService {
    private final JobListingRepository jobListingRepository;
    private final CompanyRepository companyRepository;

    public JobListingService(JobListingRepository jobListingRepository, CompanyRepository companyRepository) {
        this.jobListingRepository = jobListingRepository;
        this.companyRepository = companyRepository;
    }

    public JobListing getJobListingById(long id) {
        if (!jobListingRepository.existsById(id)) {
            throw new RuntimeException("Job Listing not found with id: " + id);
        }
        return jobListingRepository.findById(id).orElse(null);
    }

    public JobListing createJobListing(JobListing jobListing) {
        return  jobListingRepository.save(jobListing);
    }

    public JobListing updateJobListing(Long id, JobListingDto dto) {
        if (!jobListingRepository.existsById(id)) {
            throw new RuntimeException("Job Listing not found with id: " + id);
        }
        JobListing jobListingExist = getJobListingById(id);
        if (dto.title() != null) {
            jobListingExist.setTitle(dto.title());
        }
        if (dto.description() != null) {
            jobListingExist.setDescription(dto.description());
        }
        if (dto.companyId() != null) {
            jobListingExist.setCompany(companyRepository.findById(dto.companyId()).orElse(null));
        }
        if (dto.experienceLevel() != null) {
            jobListingExist.setExperienceLevel(dto.experienceLevel());
        }
        if (dto.workMode() != null) {
            jobListingExist.setWorkMode(dto.workMode());
        }
        if (dto.status() != null) {
            jobListingExist.setStatus(dto.status());
        }
        if (dto.jobType() != null) {
            jobListingExist.setJobType(dto.jobType());
        }
        return jobListingRepository.save(jobListingExist);
    }

    public List<JobListing> listJobListings() {
        return jobListingRepository.findAll();
    }

    public JobListing closeJobListing(Long id) {
        if (!jobListingRepository.existsById(id)) {
            throw new RuntimeException("Job Listing not found with id: " + id);
        }
        JobListing jobListing = getJobListingById(id);
        jobListing.setStatus(Status.CLOSED);
        return jobListingRepository.save(jobListing);
    }

    public JobListing openJobListing(Long id) {
        if (!jobListingRepository.existsById(id)) {
            throw new RuntimeException("Job Listing not found with id: " + id);
        }
        JobListing jobListing = getJobListingById(id);
        jobListing.setStatus(Status.OPEN);
        return jobListingRepository.save(jobListing);
    }

    public void deleteJobListing(Long id) {
        if (!jobListingRepository.existsById(id)) {
            throw new RuntimeException("Job Listing not found with id: " + id);
        }
        jobListingRepository.deleteById(id);
    }

    public List<JobListing> searchJobListings(String title, Long companyId, JobType jobType, WorkMode workMode, ExperienceLevel experienceLevel, Status status) {
        List<JobListing> jobListings = jobListingRepository.findAll();
        return jobListings.stream()
                .filter(jobListing -> title == null || jobListing.getTitle().contains(title))
                .filter(jobListing -> companyId == null || jobListing.getCompany() != null && jobListing.getCompany().getId().equals(companyId))
                .filter(jobListing -> jobType == null || jobListing.getJobType() == jobType)
                .filter(jobListing -> workMode == null || jobListing.getWorkMode() == workMode)
                .filter(jobListing -> experienceLevel == null || jobListing.getExperienceLevel() == experienceLevel)
                .filter(jobListing -> status == null || jobListing.getStatus() == status)
                .toList();
    }
}
