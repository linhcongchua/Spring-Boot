package com.learn.meme.repository;

import com.learn.meme.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    UserDetails getByUsername(String username);
}
