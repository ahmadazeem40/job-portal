package com.ahmed.job_portal.api;

import com.ahmed.job_portal.api.dto.CompanyDto;
import com.ahmed.job_portal.api.dto.CompanyResponseDto;
import com.ahmed.job_portal.api.dto.UserResponseDto;
import com.ahmed.job_portal.api.mapper.CompanyMapper;
import com.ahmed.job_portal.api.mapper.UserMapper;
import com.ahmed.job_portal.model.Company;
import com.ahmed.job_portal.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper, UserMapper userMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.userMapper = userMapper;
    }

    @PostMapping
    public CompanyResponseDto createCompany(@RequestBody CompanyDto dto) {
        Company company = companyMapper.toCompany(dto);
        return  companyMapper.toCompanyResponseDto(companyService.createCompany(company));
    }

    @GetMapping(path = "/{id}")
    public CompanyResponseDto getCompanyById(@PathVariable Long id) {
        return companyMapper.toCompanyResponseDto(companyService.getCompanyById(id).orElse(null));
    }

    @GetMapping
    public List<CompanyResponseDto> getCompanies() {
        return companyMapper.toCompanyResponseDto(companyService.listCompanies());
    }

    @PutMapping(path = "/{id}")
    public CompanyResponseDto updateCompany(@PathVariable Long id, @RequestBody CompanyDto dto) {
        return companyMapper.toCompanyResponseDto(companyService.updateCompany(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompanyById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User with ID " + id + " has been successfully deleted.");
        response.put("status", "SUCCESS");

        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}/users")
    public List<UserResponseDto> getCompanyUsers(@PathVariable Long id) {
        return userMapper.toUserResponseDto(companyService.listCompanyUsers(id)) ;
    }


}
