package com.ahmed.job_portal.api.mapper;

import com.ahmed.job_portal.api.dto.CompanyDto;
import com.ahmed.job_portal.api.dto.CompanyResponseDto;
import com.ahmed.job_portal.model.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyResponseDto toCompanyResponseDto(Company company);
    Company toCompany(CompanyResponseDto companyResponseDto);

    CompanyDto toCompanyDto(Company company);
    Company toCompany(CompanyDto companyDto);

    List<CompanyResponseDto> toCompanyResponseDto(List<Company> companies);
}
