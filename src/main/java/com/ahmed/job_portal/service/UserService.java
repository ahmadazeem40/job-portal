package com.ahmed.job_portal.service;

import com.ahmed.job_portal.api.dto.UserDto;
import com.ahmed.job_portal.model.Company;
import com.ahmed.job_portal.model.User;
import com.ahmed.job_portal.persistence.CompanyRepository;
import com.ahmed.job_portal.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    private UserService(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDto dto) {
        User userExist = getUserById(id).orElseThrow();
        if (dto.name() != null) {
            userExist.setName(dto.name());
        }
        if (dto.email() != null) {
            userExist.setEmail(dto.email());
        }
        if (dto.role() != null) {
            userExist.setRole(dto.role());
        }
        if (dto.education() != null) {
            userExist.setEducation(dto.education());
        }

        return userRepository.save(userExist);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        User user = getUserById(id).orElseThrow();
        userRepository.delete(user);
    }

    public User assignCompany(Long id, String companyName) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        User user = getUserById(id).orElse(null);
        Company company = companyRepository.findByCompanyName(companyName).orElseThrow();
        user.setCompany(company);
        return userRepository.save(user);
    }
}
