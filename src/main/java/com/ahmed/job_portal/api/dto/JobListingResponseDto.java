package com.ahmed.job_portal.api.dto;

import com.ahmed.job_portal.model.ExperienceLevel;
import com.ahmed.job_portal.model.JobType;
import com.ahmed.job_portal.model.Status;
import com.ahmed.job_portal.model.WorkMode;

public record JobListingResponseDto(
        String title,
        String description,
        WorkMode workMode,
        ExperienceLevel experienceLevel,
        Status status,
        JobType jobType,
        Long companyId) {
}
