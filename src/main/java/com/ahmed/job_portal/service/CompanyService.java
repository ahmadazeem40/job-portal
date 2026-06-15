package com.ahmed.job_portal.service;

import com.ahmed.job_portal.api.dto.CompanyDto;
import com.ahmed.job_portal.model.Company;
import com.ahmed.job_portal.model.JobListing;
import com.ahmed.job_portal.model.User;
import com.ahmed.job_portal.persistence.CompanyRepository;
import com.ahmed.job_portal.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    public Optional<Company> getCompanyById(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found with id: " + id);
        }
        return companyRepository.findById(id);
    }

    public Company updateCompany(Long id, CompanyDto dto) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found with id: " + id);
        }
        Company companyExist = getCompanyById(id).orElseThrow();
        if (dto.companyName() != null) {
            companyExist.setCompanyName(dto.companyName());
        }
        if (dto.address() != null) {
            companyExist.setAddress(dto.address());
        }
        if (dto.website() != null) {
            companyExist.setWebsite(dto.website());
        }

        return companyRepository.save(companyExist);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyByName(String name) {
        return companyRepository.findByCompanyName(name).orElseThrow();
    }

    public List<Company> listCompanies() {
        return companyRepository.findAll();
    }

    public void deleteCompanyById(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company not found with id: " + id);
        }
        companyRepository.deleteById(id);
    }

    public List<User> listCompanyUsers(Long id) {
        return userRepository.findByCompany_Id(id);
    }

//    public List<JobListing>  listCompanyJobs(Long id) {
//
//    }
}
