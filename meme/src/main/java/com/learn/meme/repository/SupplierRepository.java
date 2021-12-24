package com.learn.meme.repository;

import com.learn.meme.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Staff, Integer> {
}
