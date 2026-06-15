package com.ahmed.job_portal.persistence;

import com.ahmed.job_portal.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    public Optional<Company> findByCompanyName(String companyName);

    public boolean existsByCompanyName(String companyName);
}