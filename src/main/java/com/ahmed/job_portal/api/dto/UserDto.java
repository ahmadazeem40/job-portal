package com.ahmed.job_portal.api.dto;

import com.ahmed.job_portal.model.Role;

public record UserDto(String name,String education, String email, Role role) {
}
