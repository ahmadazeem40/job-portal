package com.ahmed.job_portal.api;

import com.ahmed.job_portal.api.dto.JobListingDto;
import com.ahmed.job_portal.api.dto.JobListingResponseDto;
import com.ahmed.job_portal.api.mapper.JobListingMapper;
import com.ahmed.job_portal.model.*;
import com.ahmed.job_portal.persistence.JobListingRepository;
import com.ahmed.job_portal.service.JobListingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/jobs")
public class JobListingController {
    private final JobListingService jobListingService;
    private final JobListingMapper jobListingMapper;
    private final JobListingRepository jobListingRepository;

    public JobListingController(JobListingService jobListingService, JobListingMapper jobListingMapper,
                                JobListingRepository jobListingRepository) {
        this.jobListingService = jobListingService;
        this.jobListingMapper = jobListingMapper;
        this.jobListingRepository = jobListingRepository;
    }

    @PostMapping
    public JobListingResponseDto createJobListing(@RequestBody JobListingDto dto) {
        JobListing jobListing = jobListingMapper.toJobListing(dto);
        return jobListingMapper.toJobListingResponseDto(jobListingService.createJobListing(jobListing));
    }

    @GetMapping
    public List<JobListingResponseDto> getJobListings() {
        return jobListingMapper.toJobListingResponseDto(jobListingService.listJobListings());
    }

    @GetMapping(path = "/{id}")
    public JobListingResponseDto getJobListing(@PathVariable Long id) {
        return jobListingMapper.toJobListingResponseDto(jobListingService.getJobListingById(id));
    }

    @PutMapping(path = "/{id}")
    public JobListingResponseDto updateJobListing(@PathVariable Long id, @RequestBody JobListingDto dto) {
        return jobListingMapper.toJobListingResponseDto(jobListingService.updateJobListing(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> deleteJobListing(@PathVariable Long id) {
        jobListingService.deleteJobListing(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Job Listing with ID " + id + " has been successfully deleted.");
        response.put("status", "SUCCESS");

        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}/close")
    public ResponseEntity<Map<String, String>> closeJobListing(@PathVariable Long id) {
        jobListingService.closeJobListing(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Job Listing with ID " + id + " has been successfully closed.");
        response.put("status", "SUCCESS");
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/{id}/open")
    public ResponseEntity<Map<String, String>> openJobListing(@PathVariable Long id) {
        jobListingService.openJobListing(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Job Listing with ID " + id + " has been successfully opened.");
        response.put("status", "SUCCESS");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public List<JobListingResponseDto> searchJobListings(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) JobType jobType,
            @RequestParam(required = false) WorkMode workMode,
            @RequestParam(required = false) ExperienceLevel experienceLevel,
            @RequestParam(required = false) Status status
    ) {
        return jobListingMapper.toJobListingResponseDto(jobListingService.searchJobListings(title, companyId, jobType, workMode, experienceLevel, status));
    }
}
