package com.ahmed.job_portal.api.mapper;

import com.ahmed.job_portal.api.dto.JobApplicationDto;
import com.ahmed.job_portal.api.dto.JobApplicationResponseDto;
import com.ahmed.job_portal.model.JobApplication;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {


    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "jobListing.id", target = "jobListingId")
    JobApplicationResponseDto toResponseDto(JobApplication jobApplication);

    List<JobApplicationResponseDto> toResponseDto(List<JobApplication> jobApplications);
}
