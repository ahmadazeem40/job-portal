package com.ahmed.job_portal.persistence;

import com.ahmed.job_portal.model.Role;
import com.ahmed.job_portal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> getUserByEmail(String email);

    public Optional<User> findByCompanyId(Long id);

    public List<User> findByCompany_Id(Long companyId);
}