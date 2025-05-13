package com.enis.library_management_system.repository;

import com.enis.library_management_system.entity.BorrowingRecord;
import com.enis.library_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, UUID> {
    List<BorrowingRecord> findByUser(User user);

    List<BorrowingRecord> findByDueDateBeforeAndReturnDateIsNull(LocalDate date);

}

