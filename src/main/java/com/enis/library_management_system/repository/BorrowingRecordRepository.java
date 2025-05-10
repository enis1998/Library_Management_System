package com.enis.library_management_system.repository;

import com.enis.library_management_system.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, UUID> {

}

