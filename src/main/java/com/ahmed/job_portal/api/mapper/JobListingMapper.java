package com.ahmed.job_portal.api.mapper;

import com.ahmed.job_portal.api.dto.JobListingDto;
import com.ahmed.job_portal.api.dto.JobListingResponseDto;
import com.ahmed.job_portal.model.JobListing;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobListingMapper {
    @Mapping(source = "company.id", target = "companyId")
    JobListingDto toJobListingDto(JobListing jobListing);

    @Mapping(source = "companyId", target = "company.id")
    JobListing toJobListing(JobListingDto jobListingDto);

    @Mapping(source = "company.id", target = "companyId")
    JobListingResponseDto toJobListingResponseDto(JobListing jobListing);

    List<JobListingResponseDto> toJobListingResponseDto(List<JobListing> jobListings);
}
