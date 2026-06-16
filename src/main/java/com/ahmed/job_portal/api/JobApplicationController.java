package com.ahmed.job_portal.api;

import com.ahmed.job_portal.api.dto.JobApplicationResponseDto;
import com.ahmed.job_portal.api.mapper.JobApplicationMapper;
import com.ahmed.job_portal.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/applications")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;
    private final JobApplicationMapper jobApplicationMapper;

    public JobApplicationController(JobApplicationService jobApplicationService, JobApplicationMapper jobApplicationMapper) {
        this.jobApplicationService = jobApplicationService;
        this.jobApplicationMapper = jobApplicationMapper;
    }

    @PostMapping
    public JobApplicationResponseDto applyToJob(@RequestParam Long userId, @RequestParam Long listingId) {
        return jobApplicationMapper.toResponseDto(jobApplicationService.applyToJob(userId, listingId));
    }

    @GetMapping(path = "/{id}")
    public JobApplicationResponseDto getJobApplicationById(@PathVariable Long id) {
        return jobApplicationMapper.toResponseDto(jobApplicationService.getJobApplicationById(id));
    }

    @GetMapping(path = "/user/{userId}")
    public List<JobApplicationResponseDto> getJobApplicationByUser(@PathVariable Long userId) {
        return jobApplicationMapper.toResponseDto(jobApplicationService.getJobApplicationsByUserId(userId));
    }

    @GetMapping(path = "/job/{jobId}")
    public List<JobApplicationResponseDto> getJobApplicationByJobListing(@PathVariable Long jobId) {
        return jobApplicationMapper.toResponseDto(jobApplicationService.getJobApplicationsByListingId(jobId));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> deleteJobApplicationById(@PathVariable Long id) {
        jobApplicationService.withdrawApplication(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Application with ID " + id + " has been successfully deleted.");
        response.put("status", "SUCCESS");

        return ResponseEntity.ok(response);
    }
}
